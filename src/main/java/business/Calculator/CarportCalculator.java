package business.Calculator;

import business.entities.Material;

import java.util.ArrayList;
import java.util.List;

public class CarportCalculator {


    public List<Material> flatCarportBOM(int width, int length, int roofID){
        List<Material> BOM = new ArrayList<>();

        //STOLPER: (Under 5,1M 4 stolper.. over 5,1M 6 stolper)
        if(length <= 510) {
            Material pole = new Material(24, 4);
            BOM.add(pole);
        }
        else {
            Material pole = new Material(24, 4);
            BOM.add(pole);
        }

        //SPÆR: (afstand mellem spær ca. 55cm, max 70cm)
        int amountOfRafters = Math.abs(length/55);

        int id = 0;
        if(width <= 300){
            id = 43;
        } else if (width <=360){
            id = 44;
        } else if(width <= 420){
            id = 45;
        } else if(width <= 480){
            id = 46;
        } else if(width <= 540){
            id = 47;
        } else if (width <= 600){
            id = 48;
        }

        Material rafters = new Material(id,amountOfRafters);

        BOM.add(rafters);

        //UNIVERSAL BESLAG (2 stk pr spær)
        int amountOfFittings = amountOfRafters * 2;
        Material fittingsRight = new Material(32,amountOfFittings/2);
        Material fittingsLeft = new Material(33,amountOfFittings/2);

        BOM.add(fittingsRight);
        BOM.add(fittingsLeft);


        //BESLAGSKRUER ( 9 pr beslag 250 pr pakke)
        double amountOfFittingScrews = amountOfFittings * 9;

        int amountOfFittingScrewPackages = (int) Math.ceil(amountOfFittingScrews/250);

        Material fittingScrews = new Material(35,amountOfFittingScrewPackages);

        BOM.add(fittingScrews);


        //TAG (udregnet på areal)
        int widthOfRoof = 0;


        //Udregning af bredde på taget)

        if(width <= 240){
            widthOfRoof = 240;
            id = 49;
        }else if (width <=300) {
            widthOfRoof = 300;
            id = 50;
        }
        else if (width <=360){
            widthOfRoof = 360;
            id = 51;
        } else if(width <= 420){
            widthOfRoof = 420;
            id = 52;
        } else if(width <= 480){
            widthOfRoof = 480;
            id = 53;
        }  else if (width <= 600){
            widthOfRoof = 600;
            id = 54;
        }

        //Udregning af antal
        int amountOfRoofs = (int) Math.ceil((double)length/109);

        Material roofs = new Material (id, amountOfRoofs);
        BOM.add(roofs);


        //Skruer til tag (12 pr kvm)
        int area = width/100 * length/100;
        int amountOfRoofScrews = area * 12;

        int amountOfRoofScrewPackages = (int) Math.ceil((double)amountOfRoofScrews/200);

        Material roofScrews = new Material(30, amountOfRoofScrewPackages);

        BOM.add(roofScrews);


        return BOM;
    }
}
