測試常用

@Before : 在每一個測試之前執行。
@After : 在每一個測試之後執行。
@BeforeClass : 在這個類別開始執行第一個測試之前。
@AfterClass : 在這個類別全部測試完執行


小知識：
-依賴注入Dependency injection (DI)
透過interface來解除對實體的關聯->依賴抽象，而不依賴實體

Injection 的種類
Method injection
Constructor injection
Property injection
Ambient context

Method injection
透過公開方法注入參數，在這個例子，我們將totalPrice裡的weather，提出到function當做傳入的參數。這種注入方式叫Method injection
Constructor injection
透過Constructor 建構子來注入參數，使用 Constructor injection 可以確保要注入的物件在被使用之前一定會初始化。並且透過Constructor注入的參數將不會再被修改。一般來說，Constructor injection是較推薦的方式。
Property Dependency
透過直接修改Property來注入。實際上較不常用。
Ambient context
透過修改環境物件，例Singleton。使用Ambient context是較不建議的作法。

在寫單元測試時，DI可說是非常重要的技巧，把對於某個物件的控制權移轉給第三方，解開了相依物件的耦合。之後開始Android的測試時，我們將再介紹DI的框架，讓DI的使用更方便。