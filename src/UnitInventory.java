public class UnitInventory extends Inventory<Unit> {

    public Fragment getFragment(String name) {
        for (Unit unit : getUnits().values()) {
            if (unit instanceof Fragment && unit.getName().equals(name)) {
                return (Fragment) unit;
            }
        }
        return null;
    }
}
