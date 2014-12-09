Scenario: Sort products by price(low to high)
Meta:
@tag priority:p4,type:manual

Given open home page
When go to products
And select 'Price: low to high' from 'Sort by dropdown'
Then products sorted by price 'low to high' for '5' pages

Scenario: Sort products by price(high to low)
Meta:
@tag priority:p4,type:manual

Given open home page
When go to products
And select 'Price: low to high' from 'Sort by dropdown'
Then products sorted by price 'Price: high to low' for '5' pages

Scenario: Products per page limitation
Meta:
@tag priority:p4,type:manual

Given open home page
When go to products
Then 'Items per page dropdown' not available
When select '36' from 'Items per page dropdown'
Then '36' producats available for '5' pages

Scenario: Add/Delete credit card
Meta:
@tag priority:p4,type:manual
@issue 305

Given open home page
When login
And go to My account
And click on 'Payment Info link'
