CREATE OR REPLACE PACKAGE BODY VNTICKETBO.LINHTN_TEST
AS
   /******************************************************************************
      NAME:       LINHTN_TEST
      PURPOSE:

      REVISIONS:
      Ver        Date        Author           Description
      ---------  ----------  ---------------  ------------------------------------
      1.0        2/24/2020      linhtn       1. Created this package body.
   ******************************************************************************/

   PROCEDURE GET_ALL_PRODUCT (P_CURSOR OUT REFCURSOR)
   AS
   BEGIN
      OPEN P_CURSOR FOR SELECT * FROM LINHTN_PRODUCTS;
   END;

   PROCEDURE INSERT_PRODUCT (
      P_PRODUCT_ID        OUT LINHTN_PRODUCTS.PRODUCT_ID%TYPE,
      P_PRODUCT_NAME   IN     LINHTN_PRODUCTS.PRODUCT_NAME%TYPE,
      P_DESCRIPTION    IN     LINHTN_PRODUCTS.DESCRIPTION%TYPE,
      P_STATUS         IN     LINHTN_PRODUCTS.STATUS%TYPE,
      P_CREATED_BY     IN     LINHTN_PRODUCTS.CREATED_BY%TYPE,
      P_PRICE          IN     LINHTN_PRODUCTS.PRICE%TYPE)
   AS
   BEGIN
      SELECT SEQ_LINHTN_PRODUCTS.NEXTVAL INTO P_PRODUCT_ID FROM DUAL;

      INSERT INTO LINHTN_PRODUCTS (PRODUCT_ID,
                                   PRODUCT_NAME,
                                   DESCRIPTION,
                                   STATUS,
                                   CREATED_DATE,
                                   CREATED_BY,
                                   PRICE)
           VALUES (P_PRODUCT_ID,
                   P_PRODUCT_NAME,
                   P_DESCRIPTION,
                   P_STATUS,
                   SYSDATE,
                   P_CREATED_BY,
                   P_PRICE);
   END;

   PROCEDURE DELETE_PRODUCT (P_PRODUCT_ID IN LINHTN_PRODUCTS.PRODUCT_ID%TYPE)
   AS
   BEGIN
      DELETE FROM LINHTN_PRODUCTS
            WHERE PRODUCT_ID = P_PRODUCT_ID;
   END;

   PROCEDURE UPDATE_PRODUCT (
      P_PRODUCT_ID     IN LINHTN_PRODUCTS.PRODUCT_ID%TYPE,
      P_PRODUCT_NAME   IN LINHTN_PRODUCTS.PRODUCT_NAME%TYPE,
      P_DESCRIPTION    IN LINHTN_PRODUCTS.DESCRIPTION%TYPE,
      P_STATUS         IN LINHTN_PRODUCTS.STATUS%TYPE,
      P_CREATED_DATE   IN LINHTN_PRODUCTS.CREATED_DATE%TYPE,
      P_CREATED_BY     IN LINHTN_PRODUCTS.CREATED_BY%TYPE,
      P_PRICE          IN LINHTN_PRODUCTS.PRICE%TYPE)
   AS
   BEGIN
      UPDATE LINHTN_PRODUCTS
         SET PRODUCT_NAME = P_PRODUCT_NAME,
             DESCRIPTION = P_DESCRIPTION,
             STATUS = P_STATUS,
             CREATED_DATE = P_CREATED_DATE,
             CREATED_BY = P_CREATED_BY,
             PRICE = P_PRICE
       WHERE PRODUCT_ID = P_PRODUCT_ID;
   END;
   
   PROCEDURE FIND_PRODUCTS_BY_NAME(
        P_CURSOR OUT REFCURSOR,
        P_PRODUCT_NAME IN LINHTN_PRODUCTS.PRODUCT_NAME%TYPE,
        P_PAGE_INDEX IN NUMBER,
        P_PAGE_SIZE IN NUMBER,
        P_RECORD_COUNT OUT NUMBER)
   AS
        P_FROM NUMBER;
        P_TO NUMBER;
    BEGIN
    /*paging:
        basically we need to defind  first index and last index
        first index <=  rownum  >= last index;
     example:
     {
        pageIndex = 3,
        pageSize =  5
     }
     =>  SELECT * FROM
         (SELECT AC.*, ROWNUM ROW_NUMBER
         FROM LINHTN_PRODUCTS AC
         WHERE    (PRODUCT_NAME LIKE '%g%'))
         WHERE ROW_NUMBER >= 10 AND ROW_NUMBER <= 15;
    */
      P_FROM := P_PAGE_SIZE * (P_PAGE_INDEX - 1) + 1;
      P_TO := P_PAGE_SIZE * P_PAGE_INDEX;
      SELECT COUNT (*)
        INTO P_RECORD_COUNT
        FROM LINHTN_PRODUCTS
       WHERE    PRODUCT_NAME LIKE '%' || P_PRODUCT_NAME || '%';

      OPEN P_CURSOR FOR
        SELECT * FROM
         (SELECT AC.*, ROWNUM ROW_NUMBER
         FROM LINHTN_PRODUCTS AC
         WHERE    (PRODUCT_NAME LIKE '%' || P_PRODUCT_NAME || '%'))
         WHERE ROW_NUMBER >= P_FROM AND ROW_NUMBER <= P_TO;
   END;
END LINHTN_TEST;