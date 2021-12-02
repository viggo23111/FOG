package business.Calculator;

import business.entities.Material;

import java.util.ArrayList;
import java.util.List;

public class CarportCalculator {


    public List<Material> flatCarportBOM(int width, int length, int shedWidth, int shedLength){
        List<Material> BOM = new ArrayList<>();

        BOM.add(calculatePoles(length));
        BOM.add(calculateRafters(width,length));
        BOM.add(calculateFittingsRight(width,length));
        BOM.add(calculateFittingsLeft(width,length));
        BOM.add(calculateFittingScrews(width,length));
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
        }

        //skruer til montering af vandbræt og stern, her skal altid bruges 1 pakke
        String description ="Til montering af stern & vandbrædt";
        BOM.add(new Material(34,1,description));


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
        String description = "Spær, monteres på rem";

        return rafters = new Material(id,amountOfRafters,description);
    }

    public Material calculateFittingsRight(int width, int length){
        //UNIVERSAL BESLAG (2 stk pr spær)
        Material fittingsRight;

        int amountOfFittings = calculateRafters(width,length).getAmount() * 2;

        String description = "Til montering af spær på rem";
        return fittingsRight = new Material(32,amountOfFittings/2,description);

    }

    public Material calculateFittingsLeft(int width, int length){
        //UNIVERSAL BESLAG (2 stk pr spær)
        Material fittingsLeft;

        int amountOfFittings = calculateRafters(width,length).getAmount() * 2;
        String description = "Til montering af spær på rem";
        return fittingsLeft = new Material(33,amountOfFittings/2,description);

    }

    public Material calculateFittingScrews(int width, int length){
        //skruer til beslag, 3 stk pr overflade, altså 9 pr beslag
        Material fittingScrews;


        double amountOfFittingScrews = calculateFittingsLeft(width, length).getAmount() * 2 * 9;

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
        //Udregner mængden af bræddebolte, 3 pr stolpe
        Material squareWasher;
        String description = "Til montering af rem på stolper";
        return squareWasher = new Material(37,2*calculatePoles(length).getAmount(),description);
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


}
