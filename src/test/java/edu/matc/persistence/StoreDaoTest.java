//package edu.matc.persistence;
//
//import edu.matc.entity.Store;
//import org.junit.Test;
//
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
///**
// * Created by student on 3/19/17.
// */
//public class StoreDaoTest {
//    @Test
//    public void getNearestStore() throws Exception {
//        StoreDao dao = new StoreDao();
//        List<Store> stores = dao.getNearestStore( 43.121842,
//                -89.328031, 5.0);
//        assertEquals("Not matching", 5, stores.size());
//    }
//
//}