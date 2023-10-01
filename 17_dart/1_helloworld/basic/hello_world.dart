class Point {
  double? x;
  double? y;
  double z = 0;
}

class ImmutablePoint {
  double? x;
  double? y;
}

class Person {
  final String _name;

  Person(this._name);

  // TODO 이건 안되는 데여~?
  //String greet(String who) => 'Hello, $who, I am $_name';
}

void main() {
  var point = Point();

  assert(point.x == null);
}