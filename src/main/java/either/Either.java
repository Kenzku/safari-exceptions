package either;

import java.util.function.Consumer;
import java.util.function.Function;


// Don't use this, nor write your own. Get a library!
// VAVr
public class Either<L, R> {
  private L left;
  private R right;
  private boolean isRight;
  private Either() {}

  public static <L, R> Either<L, R> success(R r) {
    Either<L, R> rv = new Either<>();
    rv.right = r;
    rv.isRight = true;
    return rv;
  }

  public static <L, R> Either<L, R> fail(L l) {
    Either<L, R> rv = new Either<>();
    rv.left = l;
    rv.isRight = false;
    return rv;
  }
  // map operation for success... DO NOT MUTATE, make new
  // mapError operation for failure... ditto...
  // forEach / ifPresent for success...
  public void ifSuccess(Consumer<R> op) {
    if (isRight) {
      op.accept(right);
    }
  }
  // forEach / ifPresent for failure (do this yourself)
  // recover from failure...
  // converts a Left-side Either to a Right side Either
  public Either<L, R> recover(Function<L, R> op) {
    if (! isRight) {
      return success(op.apply(left));
    } else {
      return this;
    }
  }
}
