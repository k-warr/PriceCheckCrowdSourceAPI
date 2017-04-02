package edu.matc.persistence;

import edu.matc.entity.Store;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

    @After
    public void tearDown() throws Exception {
        dao.deleteStore(store);

    }

    @Test
    public void addStore() throws Exception {

        assertTrue("Store ID > 0",(storeId > 0));

        store2 = dao.getStore(storeId);
        assertTrue("Stores should be equal",(store2.getStoreId() == store.getStoreId()));

    }

    @Test
    public void deleteStore() throws Exception {
        store2 = new Store();
        store2.setStoreName("JUnit Store2");
        store2.setStoreAddress("JUnit Address2");
        store2.setLongtitude(BigDecimal.valueOf(65.0));
        store2.setLatitude(BigDecimal.valueOf(65.0));

        int storeId2 = dao.addStore(store2);
        dao.deleteStore(store2);
        Store storeTemporary = dao.getStore(storeId2);

        assertNull("Should be null",storeTemporary);

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
    public void getStoreByName() throws Exception {

        assertEquals("Store id should match",(Integer) storeId, (Integer) dao
                .getStoreByName("JUnit Store").get(0));

    }

    @Test
    public void getExactStore() throws Exception {
        assertEquals("Store should match", store.getStoreId(), dao.getExactStore(store
                .getStoreName(), store.getLatitude().doubleValue(), store
                .getLongtitude().doubleValue()).get(0).getStoreId());
    }

    @Test
    public void getNearestStoreId() throws Exception {
        List<Integer> storeIds = dao.getNearestStoreId(5,5,50);
        assertEquals("Shnould equal",storeId, (int) storeIds.get(0));

    }

    @Test
    public void getNearestStore() throws Exception {
        List<Store> stores = dao.getNearestStore(5,5,50);
        assertEquals("Shnould equal",store.getStoreId(), stores.get(0).getStoreId());

    }

}