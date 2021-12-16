package business.services;

import java.util.ArrayList;
import java.util.List;

public class SVGGenerator {
    private double width;
    private double length;
    private double shedWidth;
    private double shedLength;
    private int angle;

    public SVGGenerator(double width, double length, double shedWidth, double shedLength, int angle) {
        this.width = width;
        this.length = length;
        this.shedWidth = shedWidth;
        this.shedLength = shedLength;
        this.angle = angle;
    }

    public List<SVG> generateSVGFlat() {
        List<SVG> drawings = new ArrayList<>();

        SVG outerSVGSide = new SVG(0, 0, "0 0 " + length + 75 + " 305", 100, 75);
        SVG sideView = new SVG(75, 0, "0 0 " + length + " 230", 75, 75);
        SVG outerSVG = new SVG(0, 0, "0 0 " + length + 75 + " " + width + 75, 100, 75);
        SVG svg = new SVG(75, 0, "0 0 " + length + " " + width, 75, 75);

        double amountOfRafters = Math.ceil(length / 55);

        double distance = length / (amountOfRafters - 1) - 4.5 / (amountOfRafters - 1);

        int amountOfPoles = 0;
        if (length <= 510) {
            amountOfPoles = 4;
        } else {
            amountOfPoles = 6;
        }



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
        svg.addDottedLine(30, 15, length - shedLength-30, width - 15);
        svg.addDottedLine(30, width - 15, length - shedLength-30, 15);


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

    public List<SVG> generateSVGSlope(){
        List<SVG> drawings = new ArrayList<>();

        double b = width/2;
        double c = 0;
        int amountPerSide = 0;
        int amount = 0;

        double radians = Math.toRadians(angle);
        c = b/Math.cos(radians);

        c = c-3;

        amountPerSide= (int)Math.ceil(c/33.9);

        SVG outerSVG = new SVG(0, 0, "0 0 " + length + 75 + " " + width + 75, 100, 75);
        SVG svgSlope = new SVG(75, 0, "0 0 " + length + " " + width, 75, 75);

        int amountOfPoles = 0;
        if (length <= 510) {
            amountOfPoles = 4;
        } else {
            amountOfPoles = 6;
        }

        double distanceToEdge = 17;
        if(shedLength == 0){
            distanceToEdge = 32;
        }
        int amountOfRaftersCarport = (int) Math.ceil((length-shedLength)/95);
        int amountOfRaftersShed = (int)Math.ceil(shedLength/78);

        System.out.println("amount of rafters: " + amountOfRaftersCarport);
        System.out.println("amount of rafters: " + amountOfRaftersShed);

        double distance = (length-shedLength) / (amountOfRaftersCarport - 1) - 4.5 / (amountOfRaftersCarport - 1)-distanceToEdge/(double)amountOfRaftersCarport;
        double distanceShed = (shedLength) / (amountOfRaftersShed - 1) - 4.5 / (amountOfRaftersShed - 1)-17/(double)amountOfRaftersShed;

        //outline
        svgSlope.addRect(0, 0, width, length);

        //remme
        svgSlope.addRect(0, 15, 4.5, length);
        svgSlope.addRect(0, width - 15, 4.5, length);

        //spær carport
        for (int x = 0; x < amountOfRaftersCarport; x++) {
            svgSlope.addRect(distance * x+15, 0, 600, 4.5);
        }
        //spær skur
        for (int x = 0; x < amountOfRaftersShed; x++) {
            svgSlope.addRect(distanceShed * x+length-shedLength+distanceShed, 0, 600, 4.5);
        }

        //lægter
        double distanceTop = (width-3)/2/((double)(amountPerSide-1));
        for (int x = 0; x < amountPerSide; x++) {
            svgSlope.addRect(0,distanceTop*x,4.3,length);
        }
        double distanceBottom = (width+3)/2/((double)(amountPerSide-1));
        for (int x = 0; x < amountPerSide; x++) {
            svgSlope.addRect(0,width-x*distanceBottom-4.3,4.3,length);
        }

        //toplægte
        svgSlope.addRect(0,(width-4)/2,4.3,length);

        //vandbræt
        svgSlope.addRect(0,0,width,5);
        svgSlope.addRect(0,0,width/2,5);
        svgSlope.addRect(length-5,0,width,5);
        svgSlope.addRect(length-5,0,width/2,5);


        //poles
        if (length < 510) {
            svgSlope.addRect(55, 12.575, 9.7, 9.7);
            svgSlope.addRect(55, width - 17.425, 9.7, 9.7);

            if(shedWidth == 0) {
                svgSlope.addRect(length - 55, 12.575, 9.7, 9.7);
                svgSlope.addRect(length - 55, width - 17.425, 9.7, 9.7);
            }
        }
        if (length > 480) {
            svgSlope.addRect(100, 12.575, 9.7, 9.7);
            svgSlope.addRect(100, width - 17.425, 9.7, 9.7);

            if(shedWidth == 0) {
                svgSlope.addRect(length - 100, 12.575, 9.7, 9.7);
                svgSlope.addRect(length - 100, width - 17.425, 9.7, 9.7);
            }
        }

        if (shedWidth < width - 30 && shedWidth != 0) {
            svgSlope.addRect(length - 110, width - 17.425, 9.7, 9.7);
            svgSlope.addRect(length - 15 - 4.85, 12.425, 9.7, 9.7);
            svgSlope.addRect(length - shedLength - 4.85, 12.425, 9.7, 9.7);
            svgSlope.addRect(length - 15 - 4.85, 10 + shedWidth, 9.7, 9.7);
            svgSlope.addRect(length - shedLength - 4.85, 10 + shedWidth, 9.7, 9.7);

        }
        if (shedWidth > 540) {
            svgSlope.addRect(length - 15 - 4.85, 10 + shedWidth / 2, 9.7, 9.7);
            svgSlope.addRect(length - shedLength - 4.85, 10 + shedWidth / 2, 9.7, 9.7);
        }


        if (amountOfPoles > 4) {
            svgSlope.addRect(length / 2, 12.575, 9.7, 9.7);
            svgSlope.addRect(length / 2, width - 17.425, 9.7, 9.7);

        }


        if (shedWidth == width - 30 && shedWidth != 0) {
            svgSlope.addRect(length - 17.425, 12.575, 9.7, 9.7);
            svgSlope.addRect(length - shedLength - 2.425, 12.575, 9.7, 9.7);


            svgSlope.addRect(length - 17.425, width - 17.425, 9.7, 9.7);
            svgSlope.addRect(length - shedLength - 2.425, width - 17.425, 9.7, 9.7);

        }


        //shed outline
        if (shedWidth != 0) {
            svgSlope.addLineForShed(length - shedLength, 15, length - 15, 15);
            svgSlope.addLineForShed(length - shedLength, shedWidth + 15, length - 15, shedWidth + 15);
            svgSlope.addLineForShed(length - shedLength, 15, length - shedLength, shedWidth + 15);
            svgSlope.addLineForShed(length - 15, 15, length - 15, shedWidth + 15);

        }

        outerSVG.addSvg(svgSlope);

        //messurements
        outerSVG.addArrowLine(50, 0, 50, width * 0.75);

        outerSVG.addArrowLine(75, width * 0.8, length * 0.75 + 75, width * 0.8);

        //text
        outerSVG.addRoatedText(30, width / 2 - 75, width + " cm");

        outerSVG.addText(length / 2, width * 0.85, length + " cm");

        drawings.add(outerSVG);

        //SIDEVIEW
        double a = 0;

        a = Math.tan(radians)*width/2;

        double height = 210 + a;

        SVG outerSVGSideSlope = new SVG(0, 0, "0 0 " + length + 75 + " "+height+75, 100, 75);
        SVG sideViewSlope = new SVG(75, 0, "0 0 " + length + " " + height, 75, 75);

        //outline
        sideViewSlope.addLine(0,height,length,height);


        //remme
        sideViewSlope.addRect(15,a+19.5,19.5,length-30);



        //poles
        if (length < 510) {
            sideViewSlope.addRect(55, a, 210, 9.7);
            if(shedWidth ==0) {
                sideViewSlope.addRect(length - 55, a, 210, 9.7);
            }
        }
        if (length > 480) {
            sideViewSlope.addRect(100, a, 210, 9.7);

            if(shedWidth == 0) {
                sideViewSlope.addRect(length - 100, a, 210, 9.7);
            }


        }

        if (shedWidth < width - 30 && shedWidth != 0) {
            //stople til carport
            sideViewSlope.addRect(length - 100, a, 210, 9.7);

            //stolper til skur
            sideViewSlope.addRect(length - shedLength - 15-9.7, a, 210, 9.7);
            sideViewSlope.addRect(length - 15-9.7, a, 210, 9.7);

            for (int i = 0; i < shedLength+9.7+4; i +=4) {
                sideViewSlope.addRect(length-shedLength-30+i,a,210,5);
            }

            //STOPLER FORAN SKUR
            if(length > 480) {
                sideViewSlope.addRect(length - 100, a, 210, 9.7);
            } else if(length < 510) {
                sideViewSlope.addRect(length - 55, a, 210, 9.7);
            }

        }


        if (amountOfPoles > 4) {
            sideViewSlope.addRect(length / 2, a, 210, 9.7);
        }

        //remme
        sideViewSlope.addRect(15,a+19.5,19.5,length-30);

        if (shedWidth == width - 30 && shedWidth != 0) {
            sideViewSlope.addRect(length - 15-9.7, a, 210, 9.7);
            sideViewSlope.addRect(length - shedLength - 30, a, 210, 9.7);

            //sideview
            for (int i = 0; i < shedLength+9.7+5; i +=5) {
                sideViewSlope.addRect(length-shedLength-30+i,a,210,5);
            }
        }

        //roof
        for (int i = 0; i < length; i +=7) {
            sideViewSlope.addRect(i,0,a,7);
        }

        //waterboard
        sideViewSlope.addRect(0,0,a,10);
        sideViewSlope.addRect(length-10,0,a,10);

        //rygsten
        sideViewSlope.addRect(10,0,10,length-20);

        //stern
        sideViewSlope.addRect(0,a,19.5,length);


        outerSVGSideSlope.addArrowLine(50*1.2, 0, 50*1.2, (210+a)*0.75);


        //length
        outerSVGSideSlope.addArrowLine(75, height*0.75*1.3, length * 0.75 + 75, height*0.75*1.3);

        //Shed length
        if  (shedLength != 0){
            outerSVGSideSlope.addArrowLine((length - shedLength) * .75 + 75 - 15 * .75-9.7, height * 0.75 * 1.1, length * .75 + 75 - 15 * 0.25-9.7, height * 0.75 * 1.1);
            outerSVGSideSlope.addText(length - shedLength / 2 - 90, height * 0.75 * 1.2, shedLength + " cm");
        }


        //text
        outerSVGSideSlope.addRoatedText(50*1.1, height*1.25 / 2 - 75+15, Math.round(210+a) + " cm");
        outerSVGSideSlope.addText(length / 2, height*0.75*1.25, length + " cm");


        outerSVGSideSlope.addSvg(sideViewSlope);

        drawings.add(outerSVGSideSlope);

        return drawings;
    }
}

