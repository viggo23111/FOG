package business.services;

import java.util.ArrayList;
import java.util.List;

public class SVGGenerator {
    private double width;
    private double length;
    private double amountOfRafters;
    private int amountOfPoles;
    private double shedWidth;
    private double shedLength;

    public SVGGenerator(double width, double length, double amountOfRafters, int amountOfPoles, double shedWidth, double shedLength) {
        this.width = width;
        this.length = length;
        this.amountOfRafters = amountOfRafters;
        this.amountOfPoles = amountOfPoles;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
    }


    public List<SVG> generateSVG() {
        List<SVG> drawings = new ArrayList<>();

        SVG outerSVGSide = new SVG(0, 0, "0 0 " + length + 75 + " 305", 100, 75);
        SVG sideView = new SVG(75, 0, "0 0 " + length + " 230", 75, 75);
        SVG outerSVG = new SVG(0, 0, "0 0 " + length + 75 + " " + width + 75, 100, 75);
        SVG svg = new SVG(75, 0, "0 0 " + length + " " + width, 75, 75);

        double distance = length / (amountOfRafters - 1) - 4.5 / (amountOfRafters - 1);

        //outline
        svg.addRect(0, 0, width, length);

        //remme
        svg.addRect(0, 15, 4.5, length);
        svg.addRect(0, width - 15, 4.5, length);

        //spær
        for (int x = 0; x < amountOfRafters; x++) {
            svg.addRect(distance * x, 0, 600, 4.5);
        }

        //poles
        if (length < 510) {
            svg.addRect(55, 12.575, 9.7, 9.7);
            svg.addRect(55, width - 17.425, 9.7, 9.7);

            if(shedWidth == 0) {
                svg.addRect(length - 55, 12.575, 9.7, 9.7);
                svg.addRect(length - 55, width - 17.425, 9.7, 9.7);
            }

            //sideview
            sideView.addRect(55, 20, 210, 9.7);
            if(shedWidth ==0) {
                sideView.addRect(length - 55, 20, 210, 9.7);
            }

        }
        if (length > 480) {
            svg.addRect(100, 12.575, 9.7, 9.7);
            svg.addRect(100, width - 17.425, 9.7, 9.7);

            //sideview
            sideView.addRect(100, 20, 210, 9.7);
            if (shedWidth == 0) {
                svg.addRect(length - 100, 12.575, 9.7, 9.7);
                svg.addRect(length - 100, width - 17.425, 9.7, 9.7);

                //sideview
                sideView.addRect(length - 100, 20, 210, 9.7);
            }
        }

            if (shedWidth < width - 30 && shedWidth != 0) {
                svg.addRect(length - 110, width - 17.425, 9.7, 9.7);
                svg.addRect(length - 15 - 4.85, 12.425, 9.7, 9.7);
                svg.addRect(length - shedLength - 4.85, 12.425, 9.7, 9.7);
                svg.addRect(length - 15 - 4.85, 10 + shedWidth, 9.7, 9.7);
                svg.addRect(length - shedLength - 4.85, 10 + shedWidth, 9.7, 9.7);

                //sideview
                //stople til carport
                sideView.addRect(length - 100, 20, 210, 9.7);

                //stolper til skur
                sideView.addRect(length - 15, 20, 210, 9.7);
                sideView.addRect(length - shedLength - 15, 20, 210, 9.7);
            }
            if (shedWidth > 540) {
                svg.addRect(length - 15 - 4.85, 10 + shedWidth / 2, 9.7, 9.7);
                svg.addRect(length - shedLength - 4.85, 10 + shedWidth / 2, 9.7, 9.7);
            }


        if (amountOfPoles > 4) {
            svg.addRect(length / 2, 12.575, 9.7, 9.7);
            svg.addRect(length / 2, width - 17.425, 9.7, 9.7);

            //sideview
            sideView.addRect(length / 2, 20, 210, 9.7);
        }


        if (shedWidth == width - 30 && shedWidth != 0) {
            svg.addRect(length - 17.425, 12.575, 9.7, 9.7);
            svg.addRect(length - shedLength - 2.425, 12.575, 9.7, 9.7);


            svg.addRect(length - 17.425, width - 17.425, 9.7, 9.7);
            svg.addRect(length - shedLength - 2.425, width - 17.425, 9.7, 9.7);

            //sideview
            sideView.addRect(length - 15, 20, 210, 9.7);
            sideView.addRect(length - shedLength - 15, 20, 210, 9.7);

        }


        //shed outline
        if (shedWidth != 0) {
            svg.addLineForShed(length - shedLength, 15, length - 15, 15);
            svg.addLineForShed(length - shedLength, shedWidth + 15, length - 15, shedWidth + 15);
            svg.addLineForShed(length - shedLength, 15, length - shedLength, shedWidth + 15);
            svg.addLineForShed(length - 15, 15, length - 15, shedWidth + 15);

            //sideview
            for (int i = 20; i < shedLength + 30; i += 10) {
                sideView.addDottedLine(length - shedLength - 30 + i, 40, length - shedLength - 30 + i, 230);
            }
        }


        //holeband
        svg.addDottedLine(0, 15, length - shedLength, width - 15);
        svg.addDottedLine(0, width - 15, length - shedLength, 15);


        outerSVG.addSvg(svg);

        //messurements
        outerSVG.addArrowLine(50, 0, 50, width * 0.75);

        outerSVG.addArrowLine(75, width * 0.8, length * 0.75 + 75, width * 0.8);

        //text
        outerSVG.addRoatedText(30, width / 2 - 75, width + " cm");

        outerSVG.addText(length / 2, width * 0.85, length + " cm");

        //side view _________________

        sideView.addLine(0, 230, length, 230);

        //overstærn
        sideView.addRotatedRect(0, 0, 20, length);

        //remme
        sideView.addRotatedRect(0, 20, 19.5, length);

        outerSVGSide.addArrowLine(50*1.2, 0, 50*1.2, 230*0.75);


        //length
        outerSVGSide.addArrowLine(75, 230*0.75*1.3, length * 0.75 + 75, 230*0.75*1.3);

        //Shed length
        if  (shedLength != 0){
            outerSVGSide.addArrowLine((length - shedLength) * .75 + 75 - 15 * .75, 230 * 0.75 * 1.1, length * .75 + 75 - 15 * 0.25, 230 * 0.75 * 1.1);
            outerSVGSide.addText(length - shedLength / 2 - 75 + 15, 230 * 0.75 * 1.2, shedLength + " cm");
        }

        //height other side
        outerSVGSide.addArrowLine(length * 0.75 + 75*1.2, 0+0.004*length, length * 0.75 + 75*1.2, 230*0.75);

        //text
        outerSVGSide.addRoatedText(50*1.1, 230.0*1.25 / 2 - 75+15, 230 + " cm");
        outerSVGSide.addText(length / 2, 230*0.75*1.4, length + " cm");
        outerSVGSide.addRotatedTextOtherWay(length * 0.75 + 75*1.3, 230*0.75/2, (int)Math.abs(230-length*0.0128) + " cm");


        outerSVGSide.addSvg(sideView);

        drawings.add(outerSVG);
        drawings.add(outerSVGSide);

        return drawings;
    }
}

