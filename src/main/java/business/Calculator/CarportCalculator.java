package business.Calculator;

import business.entities.Material;

import java.util.ArrayList;
import java.util.List;

public class CarportCalculator {


    public List<Material> flatCarportBOM(int width, int length, int shedWidth, int shedLength){
        List<Material> BOM = new ArrayList<>();
        String description;

        BOM.add(calculatePoles(length));
        BOM.add(calculateRafters(width,length));
        BOM.add(calculateFittingsRight(calculateRafters(width,length).getAmount()));
        BOM.add(calculateFittingsLeft(calculateRafters(width,length).getAmount()));
        BOM.add(calculateFittingScrews(calculateRafters(width,length).getAmount()));
        BOM.add(calculateAmountOfRoofItems(width,length));
        BOM.add(calculateAmountOfRoofScrews(width,length));
        BOM.add(calculateUnderFasciaBoardFrontBack(width,length));
        BOM.add(calculateUnderFasciaBoardSides(length));
        BOM.add(calculateOverFasciaBoardFront(width));
        BOM.add(calculateOverFasciaBoardSides(length));
        BOM.add(calculateFrameSides(length));
        BOM.add(calculateWeatherboardFront(width));
        BOM.add(calculateWeatherboardSides(length));
        BOM.add(calculatePerforatedTape(width,length));
        BOM.add(calculateAmountOfBoardBolts(length));
        BOM.add(calculateAmountOfSquareWashers(length));

        if(shedLength > 0){
            BOM.add(calculateCladdingBoards(shedWidth,shedLength));
            BOM.add(calculatePolesForShed(width,length,shedWidth,shedLength));
            BOM.add(calculateNoggingsGable(shedWidth));
            BOM.add(calculateNoggingsSides(shedLength));
            BOM.add(calculateScrewsForInnerCladding(shedWidth,shedLength));
            BOM.add(calculateScrewsForOuterCladding(shedWidth,shedLength));
            BOM.add(calculateAngleFittingForShed(shedWidth,shedLength));

            //Z på bagdør vil altid være det samme, da vi ikke kan ændre på dørens mål
            description = "Til z på bagside af dør";
            BOM.add(new Material(82,1,description));

            //dørgreb vil altid være det samme da vi ikke ændrer på døren
            description = "Til lås på dør i skur";
            BOM.add(new Material(40, 1,description));

            //der vil altid være to stk hængsler til døren
            description = "Til skurdør";
            BOM.add(new Material(41,2,description));
        }

        //skruer til montering af vandbræt og stern, her skal altid bruges 1 pakke
        description ="Til montering af stern & vandbrædt";
        BOM.add(new Material(34,1,description));
        return BOM;
    }

    public List<Material> slopeCarportBOM(int width, int length, int shedWidth, int shedLength, int slope, int roofID) {
        List<Material> BOM = new ArrayList<>();
        String description;

        BOM.add(calculatePoles(length));
        BOM.add(calculateFrameSides(length));
        BOM.add(calculateRaftersBottom(width,length,shedLength));
        BOM.add(calculateRaftersTop(calculateRaftersBottom(width,length,shedLength).getAmount(),width,slope));
        BOM.add(calculateFittingsRight(calculateRaftersBottom(width,length,shedLength).getAmount()));
        BOM.add(calculateFittingsLeft(calculateRaftersBottom(width,length,shedLength).getAmount()));
        BOM.add(calculateFittingScrews(calculateRaftersBottom(width,length,shedLength).getAmount()));
        BOM.add(calculateAmountOfBoardBolts(length));
        BOM.add(calculateAmountOfSquareWashers(length));
        BOM.add(calculateBargeBorads(width,slope));




        if (shedLength > 0) {
            BOM.add(calculateCladdingBoards(shedWidth, shedLength));
            BOM.add(calculatePolesForShed(width, length, shedWidth, shedLength));
            BOM.add(calculateNoggingsGable(shedWidth));
            BOM.add(calculateNoggingsSides(shedLength));
            BOM.add(calculateScrewsForInnerCladding(shedWidth, shedLength));
            BOM.add(calculateScrewsForOuterCladding(shedWidth, shedLength));
            BOM.add(calculateAngleFittingForShed(shedWidth, shedLength));

            //Z på bagdør vil altid være det samme, da vi ikke kan ændre på dørens mål
            description = "Til z på bagside af dør";
            BOM.add(new Material(82, 1, description));

            //dørgreb vil altid være det samme da vi ikke ændrer på døren
            description = "Til lås på dør i skur";
            BOM.add(new Material(40, 1, description));

            //der vil altid være to stk hængsler til døren
            description = "Til skurdør";
            BOM.add(new Material(41, 2, description));
        }
        return BOM;
    }

    public Material calculatePoles(int length){
        //STOLPER: (Under 5,1M 4 stolper.. over 5,1M 6 stolper)
        Material pole;

        String description = "Stolper nedgraves 90 cm. i jord";
        if(length <= 510) {
            return pole = new Material(24, 4,description);
        }
        else {
            return pole = new Material(24, 6,description);
        }
    }

    public Material calculateRafters(int width, int length){
        //SPÆR: (afstand mellem spær ca. 55cm, max 70cm)
        Material rafters;

        int amountOfRafters = (int) Math.ceil((double)length/55);
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
        String description = "Spær, monteres på rem";

        return rafters = new Material(id,amountOfRafters,description);
    }

    public Material calculateFittingsRight(int amountOfRafters){
        //UNIVERSAL BESLAG (2 stk pr spær)
        Material fittingsRight;

        int amountOfFittings = amountOfRafters * 2;

        String description = "Til montering af spær på rem";
        return fittingsRight = new Material(32,amountOfFittings/2,description);

    }

    public Material calculateFittingsLeft(int amountOfRafters){
        //UNIVERSAL BESLAG (2 stk pr spær)
        Material fittingsLeft;

        int amountOfFittings = amountOfRafters * 2;
        String description = "Til montering af spær på rem";
        return fittingsLeft = new Material(33,amountOfFittings/2,description);

    }

    public Material calculateFittingScrews(int amountOfRafters){
        //skruer til beslag, 3 stk pr overflade, altså 9 pr beslag
        Material fittingScrews;


        double amountOfFittingScrews = calculateFittingsLeft(amountOfRafters).getAmount() * 2 * 9;

        int amountOfFittingScrewPackages = (int) Math.ceil(amountOfFittingScrews/250);
        String description = "Til montering af universalbeslag + hulbånd";
        return fittingScrews = new Material(35,amountOfFittingScrewPackages,description);
    }

    public Material calculateAmountOfRoofItems(int width, int length){
        //Udregning af hvilken bredde tag der skal bruges
        Material roof;

        int id = 0;

        if(width <= 240){
            id = 49;
        }else if (width <=300) {
            id = 50;
        }
        else if (width <=360){
            id = 51;
        } else if(width <= 420){
            id = 52;
        } else if(width <= 480){
            id = 53;
        }  else if (width <= 600){
            id = 54;
        }

        //Udregning af antal
        int amountOfRoofItems = (int) Math.ceil((double)length/109);
        String description = "tagplader monteres på spær";
        return roof = new Material (id, amountOfRoofItems,description);
    }

    public Material calculateAmountOfRoofScrews(int width, int length){
        //udregning af antal skruer til taget, 12 pr kvm
        Material roofScrews;

        int area = width/100 * length/100;
        int amountOfRoofScrews = area * 12;

        int amountOfRoofScrewPackages = (int) Math.ceil((double)amountOfRoofScrews/200);
        String description = "Skruer til tagplader";
        return roofScrews = new Material(30, amountOfRoofScrewPackages,description);

    }

    public Material calculateUnderFasciaBoardFrontBack(int width, int length){
        //UNDERSTERNBRÆDDER til for og bagende
        Material fasciaBoard;

        int id = 0;


        if(width <= 300){
            id = 61;
        } else if(width <=360){
            id = 62;
        } else if(width <= 420){
            id = 63;
        } else if(width <= 480){
            id = 64;
        } else if(width <=540){
            id = 65;
        } else if (width <= 600){
            id = 66;
        }
        String description = "understernbrædder til for & bag ende";
        return fasciaBoard = new Material(id,2,description);
    }

    public Material calculateUnderFasciaBoardSides(int length){
        //UNDERSTERNBRÆDDER til sider
        Material fasciaBoard;

        int id = 0;

        int amountOfBoards = 0;

        if(length <= 300){
            id = 61;
            amountOfBoards = 2;
        } else if(length <=360){
            id = 62;
            amountOfBoards = 2;
        } else if(length <= 420){
            id = 63;
            amountOfBoards = 2;
        } else if(length<= 480){
            id = 64;
            amountOfBoards = 2;
        } else if(length <=540){
            id = 65;
            amountOfBoards = 2;
        } else if (length <= 600){
            id = 66;
            amountOfBoards = 2;
        } else if (length <= 720){
            id = 62;
            amountOfBoards = 4;
        } else if (length <= 840){
            id = 63;
            amountOfBoards = 4;
        } else if (length <= 960){
            id = 64;
            amountOfBoards = 4;
        } else if (length <= 1080){
            id = 65;
            amountOfBoards = 4;
        } else if (length <= 1200){
            id = 66;
            amountOfBoards = 4;
        }
        String description = "understernbrædder til siderne";
        return fasciaBoard = new Material(id, amountOfBoards,description);
    }

    public Material calculateOverFasciaBoardFront(int width){
        //OVERSTERNBRÆT til forende
        Material fasciaBoard;

        int id = 0;

        if(width <= 300){
            id = 55;
        } else if(width <=360){
            id = 56;
        } else if(width <= 420){
            id = 57;
        } else if(width <= 480){
            id = 58;
        } else if(width <=540){
            id = 59;
        } else if (width <= 600){
            id = 60;
        }
        String description = "oversternbrædder til forenden";
        return fasciaBoard = new Material(id,1,description);
    }

    public Material calculateOverFasciaBoardSides(int length){
        ////OVERSTERNBRÆT til sider
        Material fasciaBoard;

        int id = 0;
        int amountOfBoards = 0;


        if(length <= 300){
            id = 55;
            amountOfBoards = 2;
        } else if(length <=360){
            id = 56;
            amountOfBoards = 2;
        } else if(length <= 420){
            id = 57;
            amountOfBoards = 2;
        } else if(length<= 480){
            id = 58;
            amountOfBoards = 2;
        } else if(length <=540){
            id = 59;
            amountOfBoards = 2;
        } else if (length <= 600){
            id = 60;
            amountOfBoards = 2;
        } else if (length <= 720){
            id = 56;
            amountOfBoards = 4;
        } else if (length <= 840){
            id = 57;
            amountOfBoards = 4;
        } else if (length <= 960){
            id = 58;
            amountOfBoards = 4;
        } else if (length <= 1080){
            id = 59;
            amountOfBoards = 4;
        } else if (length <= 1200){
            id = 60;
            amountOfBoards = 4;
        }
        String description = "oversternbrædder til siderne";
        return fasciaBoard = new Material(id,amountOfBoards,description);
    }

    public Material calculateFrameSides(int length){
        //Brædder til remmen
        Material frame;

        int id = 0;

        int amountOfBoards = 0;

        if(length <= 300){
            id = 43;
            amountOfBoards = 2;
        } else if(length <=360){
            id = 44;
            amountOfBoards = 2;
        } else if(length <= 420){
            id = 45;
            amountOfBoards = 2;
        } else if(length<= 480){
            id = 46;
            amountOfBoards = 2;
        } else if(length <=540){
            id = 47;
            amountOfBoards = 2;
        } else if (length <= 600){
            id = 48;
            amountOfBoards = 2;
        } else if (length <= 720){
            id = 44;
            amountOfBoards = 4;
        } else if (length <= 840){
            id = 45;
            amountOfBoards = 4;
        } else if (length <= 960){
            id = 46;
            amountOfBoards = 4;
        } else if (length <= 1080){
            id = 47;
            amountOfBoards = 4;
        } else if (length <= 1200){
            id = 48;
            amountOfBoards = 4;
        }
        String description = "Remme i sider, sadles ned i stolper";
        return frame = new Material(id, amountOfBoards,description);
    }

    public Material calculateWeatherboardFront(int width){
        //Vandbræt til fronten
        Material weatherboard;

        int id = 0;

        int amountOfBoards = 0;

        if(width <= 300){
            id = 67;
            amountOfBoards = 1;
        } else if(width <=360){
            id = 68;
            amountOfBoards = 1;
        } else if(width <= 420){
            id = 69;
            amountOfBoards = 1;
        } else if(width<= 480){
            id = 70;
            amountOfBoards = 1;
        } else if(width <=600){
            id = 67;
            amountOfBoards = 2;
        } else if (width <= 720){
            id = 68;
            amountOfBoards = 2;
        } else if (width <= 840) {
            id = 69;
            amountOfBoards = 2;
        } else if(width <= 960){
            id = 70;
            amountOfBoards = 2;
        }
        String description = "vandbrædt på stern i forende";
        return weatherboard = new Material(id, amountOfBoards,description);
    }

    public Material calculateWeatherboardSides(int length){
        //vandbrædder til siderne
        Material weatherboard;

        int id = 0;

        int amountOfBoards = 0;

        if(length <= 300){
            id = 67;
            amountOfBoards = 2;
        } else if(length <=360){
            id = 68;
            amountOfBoards = 2;
        } else if(length <= 420){
            id = 69;
            amountOfBoards = 2;
        } else if(length<= 480){
            id = 70;
            amountOfBoards = 2;
        } else if (length <= 600){
            id = 67;
            amountOfBoards = 4;
        } else if (length <= 720){
            id = 68;
            amountOfBoards = 4;
        } else if (length <= 840){
            id = 69;
            amountOfBoards = 4;
        } else if (length <= 960){
            id = 70;
            amountOfBoards = 4;
        }
        String description = "vandbrædt på stern i sider";
        return weatherboard = new Material(id, amountOfBoards,description);
    }

    public Material calculatePerforatedTape(int carportWidth, int carportLength){
        //Uregner hulbånd
        Material perforatedTape;

        double width= carportWidth;
        double length = carportLength;

        int amount = 0;

        if(Math.sqrt(Math.pow(width,2) + Math.pow(length,2)) <= 500){
            amount = 1;
        } else if (Math.sqrt(Math.pow(width,2) + Math.pow(length,2)) <= 1000) {
            amount = 2;
        }
        String description = "Til vindkryds på spær";
        return perforatedTape = new Material(31,amount,description);
    }

    public Material calculateAmountOfBoardBolts(int length){
        //Udregner mængden af bræddebolte, 3 pr stolpe
        Material boardBolt;
        String description = "Til montering af rem på stolper";
        return boardBolt = new Material(36,3*calculatePoles(length).getAmount(),description);
    }

    public Material calculateAmountOfSquareWashers(int length){
        //Udregner mængden af firkantskiver
        Material squareWasher;

        double amountOfScrews = (double)calculatePoles(length).getAmount()*2;

        int amount = (int) Math.ceil(amountOfScrews/50);


        String description = "Til montering af rem på stolper";
        return squareWasher = new Material(37,amount,description);
    }

    public Material calculateCladdingBoards(int shedWidth, int shedLength){
        //Udregner beklædningsbrædder til skur
        Material claddingBoard;

        double width = shedWidth;
        double length = shedLength;

        double overlay = 2.5;
        double widthOfBoard = 10;
        double circumference = width*2 + length*2;

        int amount = (int) Math.ceil(circumference/(widthOfBoard+widthOfBoard-overlay*2)*2);
        String description = "til beklædning af skur 1 på 2 ";
        return claddingBoard = new Material(67, amount,description);
    }

    public Material calculatePolesForShed(int width, int length, int shedWidth, int shedLength){
        //beregner hvor mange ekstra stolper der skal være hvis der skal redskabsskur på.
        Material poles;
        int amount = 0;

        //her vil stolperne gå helt ud til remmen
        if (shedWidth == width-30){
            amount = 4;
        }
        //her vil der være brug for en stolpe mere i hver side, da brættet max kan være 540cm
        else if(shedWidth > 540){
            amount = 5;
        }
        else {
            amount = 3;
        }
        String description = "Ekstra stolper til skur nedgraves 90 cm. i jord";
        return poles = new Material(24,amount,description);
    }

    public Material calculateNoggingsGable(int shedWidth){
        //udrenger løsholter til gavle af skur
        Material nogging;

        int amount = 0;
        int id = 0;

        if(shedWidth > 540){
            amount = 12;
            id = 73;
        }
        else if (shedWidth <= 240){
            amount = 6;
            id = 71;
        } else if (shedWidth <= 270) {
            amount = 6;
            id = 72;
        } else if (shedWidth <= 300) {
            amount = 6;
            id = 73;
        } else if (shedWidth <= 330) {
            amount = 6;
            id = 74;
        } else if (shedWidth <= 360) {
            amount = 6;
            id = 75;
        } else if (shedWidth <= 390) {
            amount = 6;
            id = 76;
        } else if (shedWidth <= 420) {
            amount = 6;
            id = 77;
        } else if (shedWidth <= 450) {
            amount = 6;
            id = 78;
        } else if (shedWidth <= 480) {
            amount = 6;
            id = 79;
        } else if (shedWidth <= 510) {
            amount = 6;
            id = 80;
        } else {
            amount = 6;
            id = 81;
        }

        String description = "løsholter til skur gavle";
        return nogging = new Material(id,amount,description);
    }

    public Material calculateNoggingsSides(int shedLength){
        //udrenger løsholter til gavle af skur
        Material nogging;

        int amount = 0;
        int id = 0;

        if(shedLength > 540){
            amount = 8;
            id = 73;
        }
        else if (shedLength <= 240){
            amount = 4;
            id = 71;
        } else if (shedLength <= 270) {
            amount = 4;
            id = 72;
        } else if (shedLength <= 300) {
            amount = 4;
            id = 73;
        } else if (shedLength <= 330) {
            amount = 4;
            id = 74;
        } else if (shedLength <= 360) {
            amount = 4;
            id = 75;
        } else if (shedLength <= 390) {
            amount = 4;
            id = 76;
        } else if (shedLength <= 420) {
            amount = 4;
            id = 77;
        } else if (shedLength <= 450) {
            amount = 4;
            id = 78;
        } else if (shedLength <= 480) {
            amount = 4;
            id = 79;
        } else if (shedLength <= 510) {
            amount = 4;
            id = 80;
        } else {
            amount = 4;
            id = 81;
        }

        String description = "løsholter til skur sider";
        return nogging = new Material(id,amount,description);
    }

    public Material calculateAngleFittingForShed(int shedWidth, int shedLength){
        //udregner vinkelbeslag til skur, 2 pr løsholt
        Material angleFitting;

        int amount = calculateNoggingsGable(shedWidth).getAmount() * 2 + calculateNoggingsSides(shedLength).getAmount()*2;

        String description = "Til montering af løsholter i skur";
        return angleFitting = new Material(42,amount,description);
    }

    public Material calculateScrewsForInnerCladding(int shedWidth, int shedLength){
        //udrenger antal skruer til den inderste beklædning 2pr løsholt/rem, altså 6 stk pr bræt
        Material screw;

        double amountOfScrews = (double)calculateCladdingBoards(shedWidth,shedLength).getAmount()/2*6;

        int amount = (int) Math.ceil(amountOfScrews/300);


        String description = "til montering af inderste beklædning";

        return screw = new Material(39,amount,description);

    }

    public Material calculateScrewsForOuterCladding(int shedWidth, int shedLength){
        //udrenger antal skruer til den yderste beklædning 2pr løsholt/rem, altså 6 stk pr bræt
        Material screw;

        double amountOfScrews = (double)calculateCladdingBoards(shedWidth,shedLength).getAmount()/2*6;

        int amount = (int) Math.ceil(amountOfScrews/400);


        String description = "til montering af yderste beklædning";

        return screw = new Material(38,amount,description);

    }

    //Calculations for slope carport


    public Material calculateRaftersTop(int amountOfRafters, int width, int angle){
        Material rafterTop;
        int amount = 0;

        double a = 0;
        double b = (double) width/2;
        double c = 0;

        double radians = Math.toRadians(angle);

        c = b/Math.cos(radians);

        a = Math.tan(radians)*c;


        double lengthOfWood = Math.ceil(c*2 + a);


        int id = 0;

        if(lengthOfWood <= 300){
            id = 43;
            amount = 1;
        }else if(lengthOfWood <= 360){
            id = 44;
            amount = 1;
        }else if(lengthOfWood <= 420){
            id = 45;
            amount = 1;
        }else if(lengthOfWood <= 480){
            id = 46;
            amount = 1;
        }else if(lengthOfWood <= 540){
            id = 47;
            amount = 1;
        }else if(lengthOfWood <= 600){
            id = 48;
            amount = 1;
        }else if(lengthOfWood <= 720){
            id = 44;
            amount = 2;
        }else if(lengthOfWood <= 840){
            id = 45;
            amount = 2;
        }else if(lengthOfWood <= 960){
            id = 46;
            amount = 2;
        }else if(lengthOfWood <= 1080){
            id = 47;
            amount = 2;
        }else if(lengthOfWood <= 1200){
            id = 48;
            amount = 2;
        }else if(lengthOfWood > 1200){
            id = 46;
            amount = 3;
        }
        return rafterTop = new Material(id,amount*amountOfRafters,"Top af spærkonstruktion");
    }

    public Material calculateRaftersBottom(int width,int length, int shedLength){
        //SPÆR: (afstand mellem spær ca. 95, over skur 78)
        Material rafters;

        int amountOfRafters = (int) Math.ceil(((double)length-(double)shedLength)/95);

        if(shedLength != 0) {
            amountOfRafters += Math.abs(shedLength / 78);
        }

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
        String description = "Spær, monteres på rem";

        return rafters = new Material(id,amountOfRafters,description);
    }

    public Material calculateBargeBorads(int width,int angle){
        //udregner vindskeder
        Material bargeBoard;

        int amount = 0;

        double b = (double) width/2;
        double c = 0;

        double radians = Math.toRadians(angle);

        c = b/Math.cos(radians);

        c = c*4;

        System.out.println(c);


        int id = 0;

        if(c <= 300){
            id = 83;
            amount = 1;
        }else if(c <= 360){
            id = 84;
            amount = 1;
        }else if(c <= 420){
            id = 85;
            amount = 1;
        }else if(c <= 480){
            id = 86;
            amount = 1;
        }else if(c <= 540){
            id = 87;
            amount = 1;
        }else if(c <= 600){
            id = 88;
            amount = 1;
        }else if(c <= 720){
            id = 84;
            amount = 2;
        }else if(c <= 840){
            id = 85;
            amount = 2;
        }else if(c <= 960){
            id = 86;
            amount = 2;
        }else if(c <= 1080){
            id = 87;
            amount = 2;
        }else if(c <= 1200){
            id = 88;
            amount = 2;
        }else if(c > 1200){
            id = 86;
            amount = 3;
        }
        String description = "Vindskeder på rejsning";

        return bargeBoard = new Material(id,amount,description);
    }





}
