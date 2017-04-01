//package edu.matc.persistence;
//
//import edu.matc.entity.PriceFact;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by student on 3/19/17.
// */
//public class PriceFactDaoTest {
//    @Ignore
//    @Test
//    public void getItemPrice() throws Exception {
//        PriceFactDao dao = new PriceFactDao();
//        StoreDao daoStore = new StoreDao();
//        List<Integer> storeIds = daoStore.getNearestStoreId(43.121842,
//                -89.328031, 5.0);
//
//        List<PriceFact> prices = dao.getItemPrice("pEanut butter",
//                null, 0,
//                null,
//                storeIds );
//
//        assertEquals("Just error ", null, prices);
//
////    }
//    //localhost:8080/pricerequest/JSON/request/name=peanut%20butter&lat=43.182659&lon=-89.209569&distance=5
////localhost:8080/pricerequest/JSON/update/name=peanut%20butter&lat=43.182659&lon=-89.209569&distance=5
////localhost:8080/pricerequest/JSON/update/inputjson={}
//
//}