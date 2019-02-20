package gens;

import org.junit.Assert;
import org.junit.Test;

public class StoreTest {

    @Test
    public void simpleTest() {
        Store roleStore = new RoleStore(10);
        roleStore.add(new Role("Role1"));
        roleStore.add(new Role("Role2"));
        roleStore.add(new Role("Role3"));
        Role role4 = new Role("Role4");
        roleStore.add(role4);

        Assert.assertEquals(role4, roleStore.findById("Role4"));
        Assert.assertEquals(((RoleStore) roleStore).size(), 4);

        boolean role3 = roleStore.delete("Role3");
        Assert.assertEquals(((RoleStore) roleStore).size(), 3);
        Assert.assertTrue(role3);
        Role role55 = new Role("Role55");
        roleStore.replace("Role1", role55);
        Assert.assertEquals(role55, roleStore.findById("Role55"));
    }

}