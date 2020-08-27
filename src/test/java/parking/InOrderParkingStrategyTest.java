package parking;


import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static parking.ParkingStrategy.NO_PARKING_LOT;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class InOrderParkingStrategyTest {

  @Test
  public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
     * With using Mockito to mock the input parameter */
    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

    ParkingLot parkingLot = mock(ParkingLot.class);
    when(parkingLot.getName()).thenReturn("ABC");
//    when(parkingLot.isFull()).thenReturn(false);
//    List<ParkingLot> parkingLotList = new ArrayList<>();
//    parkingLotList.add(parkingLot);
    Car car = mock(Car.class);
    when(car.getName()).thenReturn("domingo");
    Receipt receipt = inOrderParkingStrategy.createReceipt(parkingLot, car);
    assertEquals("domingo", receipt.getCarName());
    assertEquals("ABC", receipt.getParkingLotName());

  }

  @Test
  public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

    /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
     * With using Mockito to mock the input parameter */
    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();

//    ParkingLot parkingLot = mock(ParkingLot.class);
//    when(parkingLot.getName()).thenReturn("ABC");
//    when(parkingLot.isFull()).thenReturn(true);
//    List<ParkingLot> parkingLotList = new ArrayList<>();
//    parkingLotList.add(parkingLot);
    Car car = mock(Car.class);
    when(car.getName()).thenReturn("domingo");
    Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(car);
    assertEquals("domingo", receipt.getCarName());
    assertEquals(NO_PARKING_LOT, receipt.getParkingLotName());

  }

  @Test
  public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
//    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
    InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
    ParkingLot parkingLot = spy(new ParkingLot("ABC",5));
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot);
    List<Car> carList = new ArrayList<>();
    for (int count=0;count<6;count++){
      carList.add(new Car(""+count));
    }
    Car car = new Car("domingo");
    doReturn(carList).when(parkingLot).getParkedCars();
    Receipt receipt = inOrderParkingStrategy.park(parkingLotList,car);

    verify(parkingLot,times(1)).getParkedCars();
    verify(inOrderParkingStrategy, times(1)).createNoSpaceReceipt(any());


  }

  @Test
  public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
    //    InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
    InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
    ParkingLot parkingLot = spy(new ParkingLot("ABC",5));
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot);
    List<Car> carList = new ArrayList<>();
    for (int count=0;count<4;count++){
      carList.add(new Car(""+count));
    }
    Car car = new Car("domingo");
    doReturn(carList).when(parkingLot).getParkedCars();
    Receipt receipt = inOrderParkingStrategy.park(parkingLotList,car);

    verify(parkingLot,times(2)).getParkedCars();
    verify(inOrderParkingStrategy, times(1)).createReceipt(parkingLot, car);
  }

  @Test
  public void testPark_givenThereIsOneFullParkingLot_thenCreateReceipt() {

    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
    InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
    ParkingLot parkingLot = spy(new ParkingLot("ABC",5));
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot);
    List<Car> carList = new ArrayList<>();
    for (int count=0;count<6;count++){
      carList.add(new Car(""+count));
    }
    Car car = new Car("domingo");
    doReturn(carList).when(parkingLot).getParkedCars();
    Receipt receipt = inOrderParkingStrategy.park(parkingLotList,car);

    verify(inOrderParkingStrategy,times(1)).createNoSpaceReceipt(car);
  }

  @Test
  public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot() {

    /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
    InOrderParkingStrategy inOrderParkingStrategy = spy(new InOrderParkingStrategy());
    ParkingLot parkingLot = spy(new ParkingLot("ABC",5));
    ParkingLot parkingLot2 = spy(new ParkingLot("BBB",8));
    List<ParkingLot> parkingLotList = new ArrayList<>();
    parkingLotList.add(parkingLot);
    parkingLotList.add(parkingLot2);
    List<Car> carList = new ArrayList<>();
    for (int count=0;count<6;count++){
      carList.add(new Car(""+count));
    }
    Car car = new Car("domingo");
    doReturn(carList).when(parkingLot).getParkedCars();
    doReturn(carList).when(parkingLot2).getParkedCars();
    Receipt receipt = inOrderParkingStrategy.park(parkingLotList,car);

    verify(inOrderParkingStrategy,times(1)).createReceipt(parkingLot2,car);
  }


}
