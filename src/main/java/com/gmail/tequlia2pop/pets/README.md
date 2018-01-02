# 说明

在随机选取一个宠物时，有两种方案：

*   随机选取一个 `Pet` 类型，来创建宠物的随机序列。

*   随机选取一个 `Pet` 注册工厂，来创建宠物的随机序列。

在第一种方案中，当需要向领域类的继承结构中添加新的 `Pet` 类型时，必须添加它对应的类字面常量。这样的问题在于修改操作不太明显，容易忽视。

最佳的做法是，在领域类基类中注册类工厂，并将将对象的创建工作交给类自己去完成（工厂方法模式）。工厂方法可以被多态地调用，从而为你创建恰当类型的对象。

在工厂的实现上有两种解决方案：

*   使用显式的工厂 `PetFactory`，需要重写基类以扩展 `PetFactory`。

*   构造器就是一种工厂方法，可以将类对象存储到 `List` 中，并使用 `newInstance()` 来创建对象。