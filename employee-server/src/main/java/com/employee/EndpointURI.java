package com.employee;

/**
 * Contains all the rest endpoint url constants
 */
public final class EndpointURI {

  private static final String BASE_API_PATH = "/api/v1/";

  /* private constructor to avoid instantiating this class. */
  private static final String ID = "/{id}";
  /* Designation API */
  public static final String DESIGNATION = BASE_API_PATH + "designation";
  public static final String DESIGNATIONS = BASE_API_PATH + "designations";
  public static final String DESIGNATION_BY_ID = DESIGNATION + ID;

  /* Department API */
  public static final String DEPARTMENT = BASE_API_PATH + "department";
  public static final String DEPARTMENTS = BASE_API_PATH + "departments";
  public static final String DEPARTMENT_BY_ID = DEPARTMENT + ID;

  /* Employee API */
  public static final String EMPLOYEE = BASE_API_PATH + "employee";
  public static final String EMPLOYEES = BASE_API_PATH + "employees";
  public static final String EMPLOYEE_BY_ID = EMPLOYEE + ID;
  
  /* Employee Family Details API */
  public static final String EMPLOYEE_FAMILY_DETAIL = BASE_API_PATH + "employee-family-detail";
  public static final String EMPLOYEE_FAMILY_DETAILS = BASE_API_PATH + "employee-family-details";
  public static final String EMPLOYEE_FAMILY_DETAIL_BY_ID = EMPLOYEE_FAMILY_DETAIL + ID;

  private EndpointURI() {

  }
}
