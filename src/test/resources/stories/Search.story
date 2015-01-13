Meta:
@capability search

Scenario: Search
Meta:
@tag priority:p1

Given user on the home page
When enter 'Automation' in 'Search field'
And press 'ENTER' for 'Search field'
Then see 'Found' message
And see 'products for' message
And 'Available products' available

Scenario: Search functionality by product name
Meta:
@tag priority:p2

Given user on the home page
When enter 'VOLTS' in 'Search field'
And press 'ENTER' for 'Search field'
Then 'Available products' available
And each product has 'VOLT' in title for '2' pages
