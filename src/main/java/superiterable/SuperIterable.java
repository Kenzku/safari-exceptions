package superiterable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;
  public SuperIterable(Iterable<E> target) {
    self = target;
  }

  // "Functor"
  // "bucket o'stuff that can create a derived bucket by applying a
  // function to everything in the original bucket
  // DO NOT MUTATE THE ORIGINAL
  public <B> SuperIterable<B> map(Function<E, B> op) {
    List<B> out = new ArrayList<>();
    for (E e : self) {
      out.add(op.apply(e));
    }
    return new SuperIterable<>(out);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }

  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(
        Arrays.asList("Fred", "Jim", "Sheila")
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

    System.out.println("---------------------");
    empty.map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
    System.out.println("---------------------");
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
