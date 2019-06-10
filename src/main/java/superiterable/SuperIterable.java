package superiterable;

import java.util.*;
import java.util.function.Function;

public class SuperIterable<E> implements Iterable<E> {
  private List<E> self;
  public SuperIterable(Iterable<E> target) {
    self = new ArrayList<>();
    target.forEach(e -> {
      if (e != null) {
        self.add(e);
      }
    });
  }

  // "Functor"
  // "bucket o'stuff that can create a derived bucket by applying a
  // function to everything in the original bucket
  // DO NOT MUTATE THE ORIGINAL
  public <B> SuperIterable<B> map(Function<E, B> op) {
    List<B> out = new ArrayList<>();
    for (E e : self) {
      B res = op.apply(e);
      if (res != null) {
        out.add(res);
      }
    }
    return new SuperIterable<>(out);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }

  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(
        Arrays.asList("Fred", "Jim", "Sheila", null)
    );

    for (String s : sis) {
      System.out.println("> " + s);
    }

    SuperIterable<String> shout = sis.map(s -> s.toUpperCase());
    for (String s : shout) {
      System.out.println(">> " + s);
    }

    sis.forEach(s -> System.out.println("I see " + s));

    SuperIterable<String> empty = new SuperIterable<>(Arrays.asList());

    Map<String, String> names = new HashMap<>();
    names.put("Fred", "Jones");
    names.put("Sheila", "Wilson");
    names.put("Bad", null);
    System.out.println("---------------------");
    sis.map(s -> names.get(s)).forEach(s -> System.out.println(s));
    System.out.println("---------------------");

//    Optional<Map<String, String>> names2 =
//    Optional<String> res = Optional.<Map<String, String>>ofNullable(null)
    Optional<String> res = Optional.<Map<String, String>>of(names)
      .map(m -> m.get("Fred"))
      .map(s -> s.toUpperCase())
      .map(s -> "Dear " + s);
      res.ifPresent(s -> System.out.println(s));
      if (!res.isPresent()) {
        System.out.println("Name not found");
      }
    ;

    System.out.println("----------------------------------");
    Optional.of(names)
        .map(m -> m.get("Bad"))
        .map(s -> s.length())
        .map(l -> "Name had " + l + " characters")
        .ifPresent(s -> System.out.println(s));
    System.out.println("----------------------------------");


    String st = Optional.of(names)
        .map(m -> m.get("Fred"))
        .map(s -> s.toUpperCase())
        .map(s -> "Dear " + s)
        .orElse("Name not found");
    System.out.println(st);
    ;



    //    String last = names.get(first);
//    String shout2 = last.toUpperCase();
//    String address = "Dear " + shout2;
//    System.out.println(address);
  }
}

/*
class UpperCaseString implements Function<String, String> {
  public String apply(String s) {
    return s.toUpperCase();
  }
}

or as a Lambda
s -> s.toUpperCase()
 */
