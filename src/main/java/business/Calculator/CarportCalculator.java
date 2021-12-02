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
        }

        //skruer til montering af vandbræt og stern, her skal altid bruges 1 pakke
        BOM.add(new Material(34,1));


        return BOM;
    }

    public Material calculatePoles(int length){
        //STOLPER: (Under 5,1M 4 stolper.. over 5,1M 6 stolper)
        Material pole;

        if(length <= 510) {
            return pole = new Material(24, 4);

        }
        else {
            return pole = new Material(24, 6);
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

        return rafters = new Material(id,amountOfRafters);
    }

    public Material calculateFittingsRight(int width, int length){
        //UNIVERSAL BESLAG (2 stk pr spær)
        Material fittingsRight;

        int amountOfFittings = calculateRafters(width,length).getAmount() * 2;
        return fittingsRight = new Material(32,amountOfFittings/2);

    }

    public Material calculateFittingsLeft(int width, int length){
        //UNIVERSAL BESLAG (2 stk pr spær)
        Material fittingsLeft;

        int amountOfFittings = calculateRafters(width,length).getAmount() * 2;
        return fittingsLeft = new Material(33,amountOfFittings/2);

    }

    public Material calculateFittingScrews(int width, int length){
        //skruer til beslag, 3 stk pr overflade, altså 9 pr beslag
        Material fittingScrews;


        double amountOfFittingScrews = calculateFittingsLeft(width, length).getAmount() * 2 * 9;

        int amountOfFittingScrewPackages = (int) Math.ceil(amountOfFittingScrews/250);

        return fittingScrews = new Material(35,amountOfFittingScrewPackages);
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

        return roof = new Material (id, amountOfRoofItems);
    }

    public Material calculateAmountOfRoofScrews(int width, int length){
        //udregning af antal skruer til taget, 12 pr kvm
        Material roofScrews;

        int area = width/100 * length/100;
        int amountOfRoofScrews = area * 12;

        int amountOfRoofScrewPackages = (int) Math.ceil((double)amountOfRoofScrews/200);

        return roofScrews = new Material(30, amountOfRoofScrewPackages);

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

        return fasciaBoard = new Material(id,2);
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

        return fasciaBoard = new Material(id, amountOfBoards);
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

        return fasciaBoard = new Material(id,1);
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

        return fasciaBoard = new Material(id,amountOfBoards);
    }

    public Material calculateFrameSides(int length){
        //UNDERSTERNBRÆDDER til sider
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

        return frame = new Material(id, amountOfBoards);
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

        return weatherboard = new Material(id, amountOfBoards);
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

        return weatherboard = new Material(id, amountOfBoards);
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

        return perforatedTape = new Material(31,amount);
    }

    public Material calculateAmountOfBoardBolts(int length){
        //Udregner mængden af bræddebolte, 3 pr stolpe
        Material boardBolt;

        return boardBolt = new Material(36,3*calculatePoles(length).getAmount());
    }

    public Material calculateAmountOfSquareWashers(int length){
        //Udregner mængden af bræddebolte, 3 pr stolpe
        Material squareWasher;

        return squareWasher = new Material(37,2*calculatePoles(length).getAmount());
    }

    public Material calculateCladdingBoards(int shedWidth, int shedLength){
        //Udregner beklædningsbrædder
        Material claddingBoard;

        double width = shedWidth;
        double length = shedLength;

        double overlay = 2.5;
        double widthOfBoard = 10;
        double circumference = width*2 + length*2;

        int amount = (int) Math.ceil(circumference/(widthOfBoard+widthOfBoard-overlay*2)*2);

        System.out.println(amount);



        return claddingBoard = new Material(67, amount);
    }


}
