package parking;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class VipParkingStrategyTest {

  @Mock
  CarDao carDao;

  @InjectMocks
  VipParkingStrategy vipParkingStrategy;

	@Test
    public void testPark_givenAVipCarAndAFullParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 4, Write a test case on VipParkingStrategy.park()
	    * With using Mockito spy, verify and doReturn */
      VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
      ParkingLot parkingLot = spy(new ParkingLot("KFC",5));
      List<ParkingLot> parkingLotList = new ArrayList<>();
      parkingLotList.add(parkingLot);
      List<Car> carList = new ArrayList<>();
      for (int count=0;count<6;count++){
          carList.add(new Car(""+count));
      }
      Car car = new Car("A001");
      doReturn(carList).when(parkingLot).getParkedCars();
      doReturn(true).when(vipParkingStrategy).isAllowOverPark(car);
      Receipt receipt = vipParkingStrategy.park(parkingLotList,car);

      verify(vipParkingStrategy,times(1)).createReceipt(parkingLot,car);
      assertEquals("KFC",receipt.getParkingLotName());
      assertEquals("A001",receipt.getCarName());

    }

    @Test
    public void testPark_givenCarIsNotVipAndAFullParkingLog_thenGiveNoSpaceReceipt() {

        /* Exercise 4, Write a test case on VipParkingStrategy.park()
         * With using Mockito spy, verify and doReturn */
        VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
        ParkingLot parkingLot = spy(new ParkingLot("KFC",5));
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(parkingLot);
        List<Car> carList = new ArrayList<>();
        for (int count=0;count<6;count++){
            carList.add(new Car(""+count));
        }
        Car car = new Car("A001");
        doReturn(carList).when(parkingLot).getParkedCars();
        doReturn(false).when(vipParkingStrategy).isAllowOverPark(car);
        Receipt receipt = vipParkingStrategy.park(parkingLotList,car);

        verify(vipParkingStrategy,times(1)).createNoSpaceReceipt(car);
    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsVipCar_thenReturnTrue(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
//      VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
//      CarDaoImpl carDao = mock(CarDaoImpl.class);
//      when(carDao.isVip(any())).thenReturn(true);
//      vipParkingStrategy.carDao = carDao;
//      Car car = new Car("A001");
//
//      assertTrue(vipParkingStrategy.isAllowOverPark(car));
      Car car=this.createMockCar("A001");
      when(carDao.isVip(car.getName())).thenReturn(true);
      assertTrue(vipParkingStrategy.isAllowOverPark(car));

    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsVipCar_thenReturnFalse(){

        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
//      VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
//      CarDaoImpl carDao = mock(CarDaoImpl.class);
//      when(carDao.isVip(any())).thenReturn(true);
//      vipParkingStrategy.carDao = carDao;
//      Car car = new Car("B001");
//
//      assertFalse(vipParkingStrategy.isAllowOverPark(car));
      Car car=this.createMockCar("B002");
      when(carDao.isVip(car.getName())).thenReturn(true);
      assertFalse(vipParkingStrategy.isAllowOverPark(car));

    }

    @Test
    public void testIsAllowOverPark_givenCarNameContainsCharacterAAndIsNotVipCar_thenReturnFalse(){
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
//      VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
//
//      CarDaoImpl carDao = mock(CarDaoImpl.class);
//      when(carDao.isVip(any())).thenReturn(false);
//      vipParkingStrategy.carDao = carDao;
//      Car car = new Car("A001");
//
//      assertFalse(vipParkingStrategy.isAllowOverPark(car));
      Car car=this.createMockCar("A002");
      when(carDao.isVip(car.getName())).thenReturn(false);
      assertFalse(vipParkingStrategy.isAllowOverPark(car));
    }

    @Test
    public void testIsAllowOverPark_givenCarNameDoesNotContainsCharacterAAndIsNotVipCar_thenReturnFalse() {
        /* Exercise 5, Write a test case on VipParkingStrategy.isAllowOverPark()
         * You may refactor the code, or try to use
         * use @RunWith(MockitoJUnitRunner.class), @Mock (use Mockito, not PowerMock) and @InjectMocks
         */
//      VipParkingStrategy vipParkingStrategy = spy(new VipParkingStrategy());
//
//      CarDaoImpl carDao = mock(CarDaoImpl.class);
//      when(carDao.isVip(any())).thenReturn(false);
//      vipParkingStrategy.carDao = carDao;
//      Car car = new Car("B001");
//
//      assertFalse(vipParkingStrategy.isAllowOverPark(car));
      Car car=this.createMockCar("B002");
      when(carDao.isVip(car.getName())).thenReturn(true);
      assertFalse(vipParkingStrategy.isAllowOverPark(car));
    }

    private Car createMockCar(String carName) {
        Car car = mock(Car.class);
        when(car.getName()).thenReturn(carName);
        return car;
    }
}
