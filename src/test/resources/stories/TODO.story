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

Scenario: Search
Meta:
@tag priority:p1,type:manual

Given open home page

Scenario: Viewed items should appear in recent views section
Meta:
@tag priority:p3

Given luser on the home page
When click 'Brands' link
Then user on the 'BrandPage'
When click 'ACME Electric' link
And open 'any' item
Given user on the 'HomePage' page
Then this product in the recent view pool

Scenario: Search functionality by product name
Meta:
@tag priority:p2

Given luser on the home page
When enter '123' in 'Main search field'
And click on 'Search icon'
Then see 'Items per page' message
And products name on the page have '123' test
