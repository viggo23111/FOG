package business.Calculator;

import business.entities.Material;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalculatorTest {

        private CarportCalculator carportCalculator = new CarportCalculator();


        //Carport med fladt tag
    @Test
    void calculateRaftersTest() {
        //Test af hvor mange spær der skal bruges, den udleverede carport fra fog skal bruge 15

        int width = 600;
        int length = 780;

        assertEquals(15,carportCalculator.calculateRafters(width,length).getAmount());
    }

    @Test
    void calculateAmountOfRoofItemsTest(){
        //Test af hvor mange trapetzplader der skal bruges, trapetzpladerne fås i forskellige bredder, men længden er altid 109
        int width = 600;
        int length = 780;

        assertEquals(8,carportCalculator.calculateAmountOfRoofItems(width,length).getAmount());

        width = 450;

        assertEquals(8,carportCalculator.calculateAmountOfRoofItems(width,length).getAmount());

        length = 570;

        assertEquals(6,carportCalculator.calculateAmountOfRoofItems(width,length).getAmount());

    }

    //Carport med rejsning

    @Test
    void calculateRaftersBottomTest() {
        //Test af hvor mange spær der skal bruges, carporten fra fog skal bruge 9
        int width = 600;
        int length = 780;
        int shedLength = 240;

        assertEquals(9,carportCalculator.calculateRaftersBottom(width,length,shedLength).getAmount());
    }

    @Test
    void calculateAmountOTilesTest(){
        //Test af hvor mange tagsten der skal bruges, tagstenene kommer i paller af 216 eller halvpaller af 108
        int width = 390;
        int length = 780;
        int slope = 20;

        assertEquals(324,carportCalculator.calculateAmountOfTiles(length,width,slope,2).getAmount());

        //Det faktiske antal tagsten der skal bruges er
        double b = width/2;
        double c = 0;

        double radians = Math.toRadians(slope);

        c = b/Math.cos(radians);

        int squareMeters = (int) Math.ceil(c/100*length/100*2);

        int amountOfTilesNeeded = squareMeters*9;

        System.out.println("Antal tagsten der er brug for: " + amountOfTilesNeeded);
    }

}