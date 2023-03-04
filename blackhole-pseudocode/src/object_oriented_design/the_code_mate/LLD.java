/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object_oriented_design.the_code_mate;

/**
 *
 * @author venka
 */

/*
requirement gathering
HLD -design componenets, communications, data

case study
design a user service
CRUD + login logout
HLD
Lb => user service =>db

LLD
 wokrs atc omponent level, design at comonent level
 a component level design process thats follows step by step refinement process. LLD is created based on HLD
LLd describes class diagrams with methods and rlationsbetween classes and programs specs.it describes modules 
so that programmer can code from document
defines teh structure, behaivour, and responsibility of the class

class diagrams
userservice
user
address
profile

converting LLD diagrams and docs to code is easier

to transform HLD to LLD we use 
1. UML diagram - unified modelling language diagram
can be used to define structure, behaviour and responsibilities of entities
used to model object oriented analysis of a software system
collection of diagrams which helps engineers, Pms and architects understand behaviour an structure of system being designed.

2. object oriented priinciples

3. SOLID principles

above 3 helps correctly identify entities and give proper respobibilities

UML benefits:
    develop a quick understanding of a software
    breaks complex systems into discrete pieces
    graphical notations can be used to communicate design decisions

2 types of UML diagrams
Structural UML diagrams
    object diagram - mainly used for devs to code
    package diagram
    component diagram
    composite structure diagram
    deloyment diagrams
    profile diagram
Bhavioural UML diagrams
    sequence diagram - sequence of behaviours/methods/operations 
    use case diagram - all operatons present in system
    activity diagram - describes the flow based on contional expressions
    state diagram
    communication diagram
    interaction overview diagram
    timing diagram


usecase diagram
    used to analyze the systems high level requirements as use cases
    each use case provides observable and valuable result to the actors.
    1. high level functional behaviour.
    2. what ssytem does from user point of view.
    3. what systems will and will not do.

usecase(operations) diagram for userservice
    1. CRUD user
    2. search user

components of usecase diagram
    system boundary - box which inlcudes responsibities, whats outside box is not its responsibility
    actors - admin(crud+ search) and user(just search) or even a system(for automated)
    use case
    includes - include necessaary interfaces
    extends - main usecase, subusecases extends main use cases

activity diagram
    shows flow of control(based on condition) for system functionality _ sequence in which it happens
    we can use activity diagram to refer steps involved in the execution of use case.
    activity represensts an operation on some class that results in a change of stateof system
    activity diagrams are used to model workflows or business processes, internal operations

example activity diagram : deleteuser operation
flows for search - > 2 possibilities(find user, not find user) etc
if found, then we can either delete it with sucess or error response etc

sequence diagram
    interations among sclasses in terms of exchange of messages over time
    calls between different objects in sequence.
two dimenstions:
    vertical - sequence of messages in chronological order.
    horizontal - object instances to which messages are sent.

example: sequece daigram for deleteuser
    refrer diagram
    userservice, transaaction, userdao

Object orentede design
    using objects to build the system
    OOP organizes the program to combine data and functionality and wrap it inside something called object

example: in ecommerce, objects like user, cart, product
user - responsible to add product to cart, do checkout, do payment + user CRUD
product - responsible to show product price, color, variations
cart - responsible for having a collection of products

to crate objects we need a class as blueprint

encapsulation => keep data + methods inside objects, make data rivate and allow usage through getter setters
                any outside entity can only use methods to interacts so adding validations to set methods is easier and prevents
                direct modifications to data
abstraction => expose only methods intended for public use, keep all data and internal methods private
Inheritance => child inherits parent objects, vehicle parent - childs - car, bus, plane etc
                common properties and behaviours are in vehicle, specific props and behaviours are only in child
polymorphism => many forms, change behaviour of methods specific to child class objects
                animal speak() may be abtsract if it doesnt need to create object for animal
                and override in child with cat speak() meowand dog speak() woof
compile time polymorphism
    compile time identification
runtime polymorphism
    runtime identification of type of reference for parent class reference type and child object type
                


*/
class User{
    private String userName;
    private String firstName;
    private String lastName;
    private Address address;
    private int age;
    public int getAge(){
        return age;
    }
    public void aetAge(int age){
        this.age = age;
    }
    public void updateAddress(Address address){
        
    }
    public Address getAddress(){
        return address;
    }
}

class Address{
    private String userName;
    private String firstName;
}
public class LLD {
    
    
    
}