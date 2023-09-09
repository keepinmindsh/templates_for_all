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