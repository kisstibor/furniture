package ro.sapientia.furniture.mock;

import ro.sapientia.furniture.model.Manufacturer;
import ro.sapientia.furniture.model.ManufacturerLocation;
import ro.sapientia.furniture.model.Stock;

import java.util.HashSet;
import java.util.List;

public class DatabaseMock {
    public static List<Manufacturer> manufacturerListWithOneManufacturer = List.of(
            new Manufacturer(1L, "Calligaris", null)
    );
    public static List<Manufacturer> manufacturerListWithMultipleManufacturers = List.of(
            new Manufacturer(1L, "Actona", null),
            new Manufacturer(3L, "De Sede", null),
            new Manufacturer(9L, "Demeyere", null),
            new Manufacturer(11L, "Gautier", null),
            new Manufacturer(13L, "Telnita", null),
            new Manufacturer(19L, "Rexwood", null)
    );
    public static List<ManufacturerLocation> manufacturerLocationsWithOneManufacturer = List.of(
            new ManufacturerLocation(
                    1L,
                    "manufacturerLocation1",
                    "address1",
                    manufacturerListWithOneManufacturer.get(0),
                    new HashSet<>(),
                    new HashSet<>(),
                    new HashSet<>()
            )
    );

    public static List<Stock> stockWithOneProduct = List.of(
            new Stock(1L, "asztallap",1111, manufacturerLocationsWithOneManufacturer.get(0))
    );



}
