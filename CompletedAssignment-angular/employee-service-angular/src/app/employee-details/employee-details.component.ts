import { Employee } from '../employee';
import { Component, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { EmployeeListComponent } from '../employee-list/employee-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  id: number;
  employee;

  constructor(private route: ActivatedRoute,private router: Router,
    private employeeService: EmployeeService) { }

  ngOnInit() {
    

    this.id = this.route.snapshot.params['id'];
    console.log(this.id)
    
    this.employeeService.getEmployee(this.id)
      .subscribe(data => {
      
        this.employee = data.results.employee;
        console.log(this.employee)
      }, error => console.log(error));
  }

  list(){
    this.router.navigate(['employees']);
  }
}
