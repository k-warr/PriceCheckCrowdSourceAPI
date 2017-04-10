package edu.matc.persistence;

import edu.matc.entity.Store;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by student on 4/1/17.
 */
public class StoreDaoTest {
    Store store;
    Store store2;
    StoreDao dao;
    int storeId;

    @Before
    public void setUp() throws Exception {
        store = new Store();
        dao = new StoreDao();

        store.setStoreName("JUnit Store");
        store.setStoreAddress("JUnit Address");
        store.setLongtitude(BigDecimal.valueOf(5.0));
        store.setLatitude(BigDecimal.valueOf(5.0));

        storeId = dao.addStore(store);

    }


    @Test
    public void addStore() throws Exception {

        assertTrue("Store ID > 0",(storeId > 0));

        store2 = dao.getStore(storeId);
        assertTrue("Stores should be equal",(store2.getStoreId() == store.getStoreId()));

    }


    @Test
    public void getAllStores() throws Exception {
        List<Store> stores = dao.getAllStores();
        boolean found = false;
        assertTrue("Nothing came back",(stores.size() > 0));

        for (Store store3: stores) {
            if (store3.getStoreId() == store.getStoreId()) {
                found = true;
            }
        }

        assertTrue("Store Should be found ",found);
    }


    @Test
    public void getExactStore() throws Exception {
        assertTrue("Store should match", dao.getExactStore(store
                .getStoreName(), store.getLatitude().doubleValue(), store
                .getLongtitude().doubleValue()).get(0).getStoreId() > 0);
    }

    @Test
    public void getNearestStoreId() throws Exception {
        List<Integer> storeIds = dao.getNearestStoreId(5,5,50);
        assertTrue("Shnould equal", (int) storeIds.get(0) > 0);

    }


    @Test
    public void getNearestStore2() throws Exception {
        StoreDao dao = new StoreDao();
        List<Store> stores = dao.getNearestStore( 43.121842,
                -89.328031, 5.0);
        assertTrue("Not matching",  stores.size() > 0);
    }

}
