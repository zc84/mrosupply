Scenario: 01. If item was added to the basketm add to basket should be changed to remove from basket button
Meta:
@tag priority:p2

Given user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And add '1' 'any' items to basket
Then see 'Product added to your cart' message
And delete from basket icon appeared for this item

Scenario: 02. Sort products by price(low to high)
Meta:
@tag priority:p4


Given user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And select 'Price: low to high' sorting
Then products sort 'by price' for '2' pages

Scenario: 03. Sort products by price(high to low)
Meta:
@tag priority:p4

Given user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And select 'Price: high to low' sorting
Then products sort 'by price desc' for '2' pages

Scenario: 04. Products per page limitation
Meta:
@tag priority:p4

Given user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
When select '36' items per page
Then '36' producats available for '2' pages

Scenario: 05. Viewed items should appear in recent views section
Meta:
@tag priority:p3

Given user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And open 'any' item
And go to 'HomePage' page
Then this product in recent view pool

Scenario: 06. User should be able to add on cart a certain amount of qty for any product on cart
Meta:
@tag priority:p3

Given user on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And add '2' 'any' items to basket
And click on 'Basket button'
Then user on the 'ShoppingCartPage'
And 'value' of 'Products quantity field' is '2'