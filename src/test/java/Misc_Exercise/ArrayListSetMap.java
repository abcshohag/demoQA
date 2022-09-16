package Misc_Exercise;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArrayListSetMap {
    //Java Collection
    // List / ArratList
    // Set / HashSet
    // Map / HashMap

    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Mahian");
        System.out.println(names.get(0));
        names.remove(0);

        ArrayList<String> countries = new ArrayList<>();
        countries.add("USA");
        System.out.println(countries.get(0));
        countries.remove(0);

        String[] addresses = new String[10];
        addresses[5] = "123 Main Street";
        System.out.println( addresses[5] );
        addresses[5] = null;

        int age;
        age = 12;
        System.out.println(age);
        Boolean isFalse = true;
    }

}
