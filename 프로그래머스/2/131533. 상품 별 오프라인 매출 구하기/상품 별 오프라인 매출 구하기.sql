-- 코드를 입력하세요
SELECT p.product_code, sum(p.price * s.sales_amount) as sales 
from offline_sale s 
join product p 
    on p.product_id = s.product_id 
group by p.product_code 
order by sales desc, product_code