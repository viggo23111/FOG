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
        BOM.add(calculateFittingScrewsSlope(calculateRaftersBottom(width,length,shedLength).getAmount(),calculateTopHolders(length).getAmount()));
        BOM.add(calculateAmountOfBoardBolts(length));
        BOM.add(calculateAmountOfSquareWashers(length));
        BOM.add(calculateBargeBorads(width,slope));
        BOM.add(calculateAmountOfTiles(length, width,slope,roofID));
        BOM.add(calculateRidgeStones(length,roofID));
        BOM.add(calculateTopHolders(length));
        BOM.add(calculateRidgeStoneFittings(roofID));
        BOM.add(calculateAmountOfBattens(width,slope,length));
        BOM.add(calculateTileBinders(calculateAmountOfBattens(width,slope,length).getAmount(), calculateAmountOfTiles(length,width,slope,roofID).getAmount()));
        BOM.add(calculateVergeClips(calculateAmountOfBattens(width,slope,length).getAmount(), calculateAmountOfTiles(length,width,slope,roofID).getAmount()));
        BOM.add(calculateTopBatten(length));
        BOM.add(calculateCladdingBoardsForRoof(width,slope));
        BOM.add(calculateFasciaBoardCarport(length,shedLength));
        BOM.add(calculateLathingBoard(length));
        BOM.add(calculateWeatherBoard(width,slope));
        BOM.add(calculateScrewsForBattens(calculateAmountOfBattens(width,slope,length).getAmount(),calculateRaftersBottom(width,length,shedLength).getAmount(),length));
        BOM.add(calculateScrewsForInnerCladdingSlope(shedWidth,shedLength,slope,width));
        BOM.add(calculateScrewsForOuterCladdingSlope(shedWidth,shedLength,width,slope));
        BOM.add(calculateHolePlateWide(calculateRaftersBottom(width,length,shedLength).getAmount()));
        BOM.add(calculateHolePlateSlim(calculateRaftersBottom(width,length,shedLength).getAmount()));
        BOM.add(calculateScrewsForHoleplates(calculateHolePlateWide(calculateRaftersBottom(width,length,shedLength).getAmount()).getAmount(),calculateHolePlateSlim(calculateRaftersBottom(width,length,shedLength).getAmount()).getAmount()));

        //SKruer til montering af stern, vindskeder, vindkryds & vandbræt, her skal altid være 1 pakke af 200
        description = "Til montering af Stern, vindskeder, vindkryds & vand bræt";
        BOM.add(new Material(34,1,description));

        if (shedLength > 0) {
            BOM.add(calculateCladdingBoards(shedWidth, shedLength));
            BOM.add(calculatePolesForShed(width, length, shedWidth, shedLength));
            BOM.add(calculateNoggingsGable(shedWidth));
            BOM.add(calculateNoggingsSides(shedLength));
            BOM.add(calculateAngleFittingForShed(shedWidth, shedLength));
            BOM.add(calculateFasciaBoardShed(shedLength));

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
        if (shedWidth == width-30 && shedWidth >=570){
            amount = 4;
        } else if (shedWidth == width-30 && shedWidth <570){
            amount = 2;
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

        a = Math.tan(radians)*width/2;


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
            amountOfRafters += Math.abs((double)shedLength / 78);
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

    public Material calculateAmountOfTiles(int length, int width, int angle, int roofID){
        //udregner tagsten til carport med rejsning, 9stk pr kvm

        Material roofTiles;

        double b = width/2;
        double c = 0;

        double radians = Math.toRadians(angle);

        c = b/Math.cos(radians);

        int squareMeters = (int) Math.ceil(c/100*length/100*2);
        int tilesNeeded = squareMeters*9;
        int amount=0;

        if(tilesNeeded <= 108){
            amount = 108;
        } else if (tilesNeeded <=216){
            amount = 216;
        } else if (tilesNeeded <= 324){
            amount = 324;
        } else if (tilesNeeded <= 432){
            amount = 432;
        }else if (tilesNeeded <= 540 ){
            amount = 540;
        }else if (tilesNeeded <= 648 ){
            amount = 648;
        }else if (tilesNeeded <= 756 ){
            amount = 756;
        }else if (tilesNeeded <= 864 ){
            amount = 864;
        }

        String description = "Monteres på taglægter";
        int id = 0;

        switch (roofID){
            case 2:
                id = 90;
                break;
            case 3:
                id = 91;
                break;
            case 4:
                id = 92;
            break;
            case 5:
                id = 89;
            break;
        }
        return  roofTiles = new Material(id,amount,description);
    }

    public Material calculateRidgeStones(int length, int roofID){
        //beregner rygningsten 3 pr meter
        Material ridgeStone;

        int amount = (int)Math.ceil((double)  length/100*3);
        String description = "Monteres på toplægte med medfølgende beslag";

        int id = 0;

        switch (roofID){
            case 2:
                id = 94;
                break;
            case 3:
                id = 95;
                break;
            case 4:
                id = 96;
                break;
            case 5:
                id = 93;
                break;
        }

        return ridgeStone = new Material(id,amount,description);
    }

    public Material calculateTopHolders(int length){
        //bergner antallet af toplægte holdere, 1 pr meter
        Material topHolder;
        int amount = (int)Math.ceil((double) length/100);

        String description = "Monteres på toppen af spæret til toplægte";

        return topHolder = new Material(97,amount,description);
    }

    public Material calculateRidgeStoneFittings(int roofID){
        //finder den rigtige farve af rygstensbeslag, der skal altid være en pakke på 50
        Material ridgeStoneFitting;

        int id = 0;

        switch (roofID){
            case 2:
                id = 99;
                break;
            case 3:
                id = 100;
                break;
            case 4:
                id = 101;
                break;
            case 5:
                id = 98;
                break;
        }

        String description ="Til fastgørelse af rygningsten";

        return ridgeStoneFitting = new Material(id,1,description);
    }

    public Material calculateAmountOfBattens(int width, int angle, int length){
        //bergener lægter afstand til toplægte 30mm, afstand mellem lægter ca 339mm

        Material battens;

        double b = width/2;
        double c = 0;
        int amountPerSide = 0;
        int amount = 0;

        double radians = Math.toRadians(angle);
        c = b/Math.cos(radians);

        c = c-3;

        amountPerSide= (int)Math.ceil(c/33.9);

        int id = 0;

        if(length <= 360){
            id = 102;
            amount = amountPerSide*2;
        } else if(length <=420){
            id = 103;
            amount = amountPerSide*2;
        } else if(length <=480){
            id = 104;
            amount = amountPerSide*2;
        } else if (length <= 720){
            id = 102;
            amount = amountPerSide*2*2;
        } else if (length <= 840){
            id = 103;
            amount = amountPerSide*2*2;
        }

        String description = "Til montering på spær, " + amountPerSide + " rækker lægter på hver side";

        return battens = new Material(id,amount,description);
    }

    public Material calculateTileBinders(int amountOfBattens,int amountOfTiles){
        //bergener mængden af tagstensbindere 1 til alle yderste tagsten + 1 til hver anden tagsten fås i pakke af 200
        Material tileBinder;
        int amountNeeded = (amountOfTiles-((amountOfBattens-2)*4))/2+((amountOfBattens-2)*4);
        int amount = 0;

        if(amountNeeded<200){
            amount = 1;
        } else if( amountNeeded <= 400){
            amount = 2;
        }else if( amountNeeded <= 600){
            amount = 3;
        }

        String description = "Til montering af tagsten, alle ydersten + hver anden fastgøres";

        return tileBinder = new Material(105,amount,description);
    }

    public Material calculateVergeClips(int amountOfBattens,int amountOfTiles){
        //bergener mængden af tagstens nakkekroge 1 til alle yderste tagsten + 1 til hver anden tagsten fås i pakke af 100
        Material vergeClip;
        int amountNeeded = (amountOfTiles-((amountOfBattens-2)*4))/2+((amountOfBattens-2)*4);
        int amount = 0;

        if(amountNeeded<100){
            amount = 1;
        } else if( amountNeeded <= 200){
            amount = 2;
        }else if( amountNeeded <= 300){
            amount = 3;
        }else if( amountNeeded <= 400){
            amount = 4;
        }else if( amountNeeded <= 500){
            amount = 5;
        }else if( amountNeeded <= 600){
            amount = 6;
        }

        String description = "Til montering af tagsten, alle ydersten + hver anden fastgøres";

        return vergeClip = new Material(106,amount,description);
    }

    public Material calculateTopBatten(int length){
        //beregner antal af brædder til toplægte, toplægten skal være lige så lang som carporten
        Material batten;

        int id = 0;
        int amount = 0;

        if(length <= 360){
            id = 102;
            amount = 2;
        } else if(length <=420){
            id = 103;
            amount = 2;
        } else if(length <=480){
            id = 104;
            amount = 2;
        } else if (length <= 720){
            id = 102;
            amount = 4;
        } else if (length <= 840){
            id = 103;
            amount = 4;
        }
        String description = "Toplægte til montering af rygningsten lægges i toplægteholder";

        return batten = new Material(id,amount,description);
    }

    public Material calculateCladdingBoardsForRoof(int width, int angle){
        //beregner antal brædder der skal bruges til at lave 1 på 2 beklædning af tagets gavle
        Material claddingBoard;



        double a = 0;
        double b = (double) width/2;
        double c = 0;
        double radians = Math.toRadians(angle);
        c = b/Math.cos(radians);
        a = Math.tan(radians)*c;

        double area = 0.5*a*width;
        double areaOfBoard = 7.5*300;

        int amount = (int)Math.ceil(area/areaOfBoard*2*2);

        String description = "til beklædning af tagets gavle 1 på 2 ";
        return claddingBoard = new Material(67, amount,description);
    }

    public Material calculateFasciaBoardCarport(int length,int shedLength){
        //beregner sternbrædder til carport del
        Material facsiaBoard;
        int lengthNeeded = length -shedLength;
        int id = 0;
        int amount = 0;

        if(lengthNeeded <= 300){
            id = 83;
            amount = 2;
        } else if(lengthNeeded <= 360){
            id = 84;
            amount = 2;
        }else if(lengthNeeded <= 420){
            id = 85;
            amount = 2;
        }else if(lengthNeeded <= 480){
            id = 86;
            amount = 2;
        }else if(lengthNeeded <= 540){
            id = 87;
            amount = 2;
        }else if(lengthNeeded <= 600){
            id = 88;
            amount = 2;
        }else if(lengthNeeded <= 720){
            id = 84;
            amount = 4;
        }else if(lengthNeeded <= 840){
            id = 85;
            amount = 4;
        }

        String description = "";
        if(shedLength != 0) {
             description = "Sternbrædder til siderne af carport del";
        } else{
             description = "Sternbrædder til siderne";
        }

        return facsiaBoard = new Material(id,amount,description);
    }

    public Material calculateFasciaBoardShed(int shedLength){
        //beregner sternbrædder til skur del
        Material facsiaBoard;

        int id = 0;
        int amount = 0;

        if(shedLength <= 300){
            id = 83;
            amount = 2;
        } else if(shedLength <= 360){
            id = 84;
            amount = 2;
        }else if(shedLength <= 420){
            id = 85;
            amount = 2;
        }else if(shedLength <= 480){
            id = 86;
            amount = 2;
        }else if(shedLength <= 540){
            id = 87;
            amount = 2;
        }else if(shedLength <= 600){
            id = 88;
            amount = 2;
        }else if(shedLength <= 720){
            id = 84;
            amount = 4;
        }else if(shedLength <= 840){
            id = 85;
            amount = 4;
        }

        String description = "Sternbrædder til siderne af skur del";


        return facsiaBoard = new Material(id,amount,description);
    }

    public Material calculateLathingBoard(int length){
        //bergener antal af brædder der skal bruges til afstandsliste
        Material lathingBoard;
        int id = 0;
        int amount = 0;

        if(length <= 300){
            id = 107;
            amount = 2;
        } else if(length <= 330){
            id = 108;
            amount = 2;
        }else if(length <= 360){
            id = 109;
            amount = 2;
        }else if(length <= 390){
            id = 110;
            amount = 2;
        }else if(length <= 420){
            id = 111;
            amount = 2;
        }else if(length <= 450){
            id = 112;
            amount = 2;
        }else if(length <= 480){
            id = 113;
            amount = 2;
        }else if(length <= 510){
            id = 114;
            amount = 2;
        }else if(length <= 540){
            id = 115;
            amount = 2;
        }else if(length <= 600){
            id = 107;
            amount = 4;
        }else if(length <= 630){
            id = 111;
            amount = 3;
        }else if(length <= 720){
            id =113;
            amount = 3;
        }else if(length <= 780){
            id = 115;
            amount = 3;
        }
        String description = "Til montering oven på tagfodslægte";

        return lathingBoard = new Material(id,amount,description);
    }

    public Material calculateWeatherBoard(int width, int angle){
        //bergerner længden af vandbræt der skal bruges
        Material weatherBoard;

        double b = width/2;
        double c = 0;

        double radians = Math.toRadians(angle);

        c = b/Math.cos(radians);

        int length = (int)Math.ceil(c*4);

        int id = 0;
        int amount = 0;

        if(length <= 300){
            id = 67;
            amount = 1;
        } else if(length <= 360){
            id = 68;
            amount = 1;
        } else if(length <= 420){
            id = 69;
            amount = 1;
        }else if(length <= 480){
            id = 70;
            amount = 1;
        } else if(length <= 600){
            id = 67;
            amount = 2;
        }else if(length <= 720){
            id = 68;
            amount = 2;
        }else if(length <= 840){
            id = 69;
            amount = 2;
        }else if(length <= 960){
            id = 70;
            amount = 2;
        }else if(length <= 1080){
            id = 68;
            amount = 3;
        }else if(length <= 1260){
            id = 69;
            amount = 3;
        }else if(length <= 1440){
            id = 70;
            amount = 3;
        }else if(length <= 1680){
            id = 69;
            amount = 4;
        }else if(length <= 1920){
            id = 70;
            amount = 4;
        }

        String description = "Vand bræt på vindskeder";

        return weatherBoard = new Material(id,amount,description);
    }

    public Material calculateScrewsForBattens(int amountOfbattens, int amountOfRafters, int length){
        //bergener antal skruer der skal bruges til at fastne taglægter, 1 skrue skal bruges hver gang en taglæte krydser et spær
        Material screw;
        int amountNeeded = 0;

        if(length <= 720) {
            amountNeeded = amountOfbattens * amountOfRafters;
        } else{
            amountNeeded = amountOfbattens/2 * amountOfRafters;
        }


        int amount = 0;
        int id = 116;

        if (amountNeeded <= 200){
            amount = 1;
        } else if (amountNeeded <= 400){
            amount = 2;
        } else if (amountNeeded <= 600){
            amountNeeded = 3;
        }

        String description = "Til taglægter";

        return screw = new Material(id,amount,description);
    }

    public Material calculateScrewsForInnerCladdingSlope(int shedWidth, int shedLength,int angle, int width){
        //udrenger antal skruer til den inderste beklædning 2pr løsholt/rem, altså 6 stk pr bræt
        Material screw;

        double amountOfScrews = (double)calculateCladdingBoardsForRoof(width,angle).getAmount()/2*4;


        if(shedLength != 0) {
            amountOfScrews += (double) calculateCladdingBoards(shedWidth, shedLength).getAmount() / 2 * 6;
        }

        int amount = (int) Math.ceil(amountOfScrews/300);


        String description = "til montering af inderste beklædning";

        return screw = new Material(39,amount,description);

    }

    public Material calculateScrewsForOuterCladdingSlope(int shedWidth, int shedLength,int width, int angle){
        //udrenger antal skruer til den yderste beklædning 2pr løsholt/rem, altså 6 stk pr bræt
        Material screw;

        double amountOfScrews = (double)calculateCladdingBoardsForRoof(width,angle).getAmount()/2*4;

        if(shedLength != 0) {
            amountOfScrews += (double) calculateCladdingBoards(shedWidth, shedLength).getAmount() / 2 * 6;
        }

        int amount = (int) Math.ceil(amountOfScrews/400);


        String description = "til montering af yderste beklædning";

        return screw = new Material(38,amount,description);
    }

    public Material calculateFittingScrewsSlope(int amountOfRafters, int amountOfTopHolders){
        //skruer til beslag, 3 stk pr overflade, altså 9 pr beslag
        Material fittingScrews;


        double amountOfFittingScrews = calculateFittingsLeft(amountOfRafters).getAmount() * 2 * 9;
        amountOfFittingScrews += amountOfTopHolders*4;

        int amountOfFittingScrewPackages = (int) Math.ceil(amountOfFittingScrews/250);
        String description = "Til montering af universalbeslag + toplægte";
        return fittingScrews = new Material(35,amountOfFittingScrewPackages,description);
    }

    public Material calculateHolePlateWide(int amountOfRafters){
        //bergener antal af brede hulplader til spær konstruktion 2 pr spær
        Material holePlate;

        int amount = amountOfRafters*2;

        String description ="Hulplade til spærkonstruktion";

        return holePlate = new Material(117,amount,description);
    }

    public Material calculateHolePlateSlim(int amountOfRafters){
        //bergener antal af smalle hulplader til spær konstruktion 6 pr spær
        Material holePlate;

        int amount = amountOfRafters*6;

        String description ="Hulplade til spærkonstruktion";

        return holePlate = new Material(118,amount,description);
    }

    public Material calculateScrewsForHoleplates(int amountOfWideHoleplates, int amountOfSlimHoleplates){
        //bregner skruer der skal bruges til at montere hulplader, 12 pr bred hulplade 8 pr smal
        Material screw;

        int amount = (int)Math.ceil(((double)amountOfWideHoleplates*12+(double)amountOfSlimHoleplates*8)/250);

        String description ="Til montering af hulplader";

        return screw = new Material(119,amount,description);
    }




}
