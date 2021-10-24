package com.example.petstore.db;
import java.util.ArrayList;
import java.util.List;
import com.example.petstore.Pet;
import com.example.petstore.Type;
import com.example.petstore.TypeResource;

public class DB {
    private static List<Pet> petTable=new ArrayList<Pet>();
    private static List<Type> typeTable=new ArrayList<Type>();

    public static List<Pet> getPetTable(){
        return petTable;
    }
    public static Pet savePet(Pet pet){
        petTable.add(pet);
        return pet;
    }
    public static Pet updatePet(Pet pet, Pet newpet){
        pet.setPetName(newpet.getPetName());
        pet.setPetAge(newpet.getPetAge());
        pet.setPetType(newpet.getPetType());
        return pet;
    }
    public static Pet deletePet(Pet pet){
        petTable.remove(pet);
        return pet;
    }


    public static List<Type> getTypeTable(){
        return typeTable;
    }
    public static Type savePetType(Type type){
        typeTable.add(type);
        return type;
    }
    public static Type updateType(Type type, Type newType){
        type.setPetTypeId(newType.getPetTypeId());
        type.setPetTypeName(newType.getPetTypeName());
        return type;
    }
    public static Type deleteType(Type type){
        typeTable.remove(type);
        return type;
    }
}
