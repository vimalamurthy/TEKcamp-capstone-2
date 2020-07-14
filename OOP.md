Object Oriented Programming Concept Questions

As you should know by now, there are 4 pillars of Object Oriented Programming.

********************
1. Encapsulation
    Encapsulation is defined as the wrapping up of data and methods under a single unit. It is the 
mechanism that binds together code and the data it manipulates. 
    The whole idea behind encapsulation is to hide the implementation details from users. 
If a data member is private it means it can only be accessed within the same class. No 
outside class can access private data member (variable) of other class. 
   How to implement encapsulation in java:
    1) Make the instance variables private so that they cannot be accessed directly from outside the class. You can only set and get values of these variables through the methods of the class.
    2) Have getter and setter methods in the class to set and get the values of the fields.

Example:

public class Hangman{

    private String word;
    private static int life = 6;

    public Hangman(String word, int life) {
        this.word = word.toUpperCase();
        this.life = life;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}


********************
2. Inheritance
    Inheritance is an important pillar of OOP(Object Oriented Programming). It is the 
mechanism in java by which one class is allow to inherit the features(fields and 
methods) of another class.
Super Class: The class whose features are inherited is known as super class(or
 a base class or a parent class).
Sub Class: The class that inherits the other class is known as sub class(or a
 derived class, extended class, or child class). The subclass can add its own 
 fields and methods in addition to the superclass fields and methods.
Reusability: Inheritance supports the concept of “reusability”, i.e. when we 
want to create a new class and there is already a class that includes some of 
the code that we want, we can derive our new class from the existing class. 
By doing this, we are reusing the fields and methods of the existing class.

Example:

class Superclass {
   int age;

   Superclass(int age) {
      this.age = age; 		 
   }

   public void getAge() {
      System.out.println("The value of the variable named age in super class is: " +age);
   }
}

public class Subclass extends Superclass {
   Subclass(int age) {
      super(age);
   }

   public static void main(String args[]) {
      Subclass s = new Subclass(24);
      s.getAge();
   }
}

********************
3. Abstraction
Abstraction is a process of hiding the implementation details and showing only 
functionality to the user.
In Java, abstraction is achieved using Abstract classes and interfaces.
A class which contains the abstract keyword in its declaration is known as abstract
 class.
Abstract classes may or may not contain abstract methods, i.e., methods without 
body ( public void get(); )
But, if a class has at least one abstract method, then the class must be declared 
abstract.
If a class is declared abstract, it cannot be instantiated.
To use an abstract class, you have to inherit it from another class, provide 
implementations to the abstract methods in it.
If you inherit an abstract class, you have to provide implementations to all the 
abstract methods in it.

Example :

abstract class HangmanGame extends JFrame implements IGame
{
 abstract String chosenWord();
}

class HangmanSinglePlayer extends HangmanGame implements IGame{
        public String chosenWord(){
        Random random = new Random();
        String[] mysteryWords ={"India","America", "Japan", "United Kingdom","Srilanka","Mexico","Thailand", "Switzerland","Cambodia", "Singapore", "Malaysia"};
        String chosen = mysteryWords[random.nextInt(mysteryWords.length)];
        return chosen;
    }
}

********************
4. Polymorphism
   
Polymorphism in Java is a concept by which we can perform a single action in different ways.
There are two types of polymorphism in Java: compile-time polymorphism and runtime 
polymorphism. We can perform polymorphism in java by method overloading and method 
overriding.

Example:

    @Override
    public void instructions() {
        JOptionPane.showMessageDialog(HangmanGame.super.getContentPane(), "There are two game options to choose from - \n" +
                " 1. Play with computer, \n" +
                " 2. Play with another player. ");
    }


Please write a 1-3 paragraphs explaining these 4 concepts further.  Please provide 
a sufficient enough explanation about these pillars, as well as some examples to 
illustrate the practical use cases of these principles.  



