# Hello! Dart

## Dart의 타입 시스템에 대해서 충분히 이해하고 있을 필요가 있음 

- [https://dart.dev/language/type-system](https://dart.dev/language/type-system)

```dart
class Animal {
  void chase(Animal a) {}
  // fixme 해당 코드가 가지는 의미는? 
  Animal get parent => {};
}
```

## Dart의 Generics 은? 

- [https://dart.dev/language/generics](https://dart.dev/language/generics)

## Dart의 객체 합성 방식 

```dart
abstract interface class Inter {
  void helloWorld();
}

class Impl implements Inter {
  @override
  void helloWorld() {
    print("Hi My Name is Interface!");
  }
}

class Proxy {
  Proxy(Inter inter){
    print("A");
    inter.helloWorld();
    print("C");
  }
}
```

## Dart에서의 디자인패턴을 정의하는 방식 / 예제 

Gof에서 제공하는 23가지 패턴에 대해서 적용 가능 

- Abstract Factory, Factory Method, Singleton, Builder, ProtoType, Composite, Chain Of Responsibility, Visitor, State, Interpreter
- Strategy, Proxy, Adapter, Memento, Mediator, Template Method, Pacade, Observer, Command, FlyWeight, Iterator, Bridge, Decorator 

```dart

enum ProductType { Human1, Human2, Human3 }

abstract interface class Product {
  void working();
}

class Human1 implements Product {
  @override
  void working() {
    // TODO: implement working
  }
}

class Human2 implements Product {
  @override
  void working() {
    // TODO: implement working
  }
}

class Human3 implements Product {
  @override
  void working() {
    // TODO: implement working
  }
}

class AbstractFactory {
  MakeProduct(ProductType productType){
    switch (productType) {
      case ProductType.Human1 :
        return Human1();
      case ProductType.Human2:
        return Human2();
      case ProductType.Human3:
        return Human3();
    }
  }
}

```