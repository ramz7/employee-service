import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
// import swal from 'sweetalert';

class App extends Component {

  constructor() {

    super();
    this.state = {
      employeeList: [],
      addEmployee: false,
      editIndex: null,
      setEmployee: null
    }
    this.updateFirstName = this.updateFirstName.bind(this)
    this.updateLastName = this.updateLastName.bind(this)
    this.updateEmail = this.updateEmail.bind(this)
    this.updateNIC = this.updateNIC.bind(this)

  }
  componentDidMount() {
    this.getAll();
  }

  async getAll() {
    try {
      await fetch('http://localhost:1724/employee-system/api/v1/employees', {
        method: 'GET',
      }).then(response => {
        console.log(response)
        if (response.status === 200) {
          response.json().then(data => {
            console.log(data);
            if (data.length !== 0) {
              this.setState({ employeeList: data.results.employees });
              this.setState({ addEmployee: false })
            } else {
              this.setState({ noData: true });
            }
          })
        }
      })
    } catch (error) {
      console.error(error);
    }
  }



  //Event Functions

  login() {
    const email = document.getElementById(`email`).value;
    const password = document.getElementById('password').value;
    email === "admin@domain.com" && password === "admin" ? this.setState({
      user: {
        email: email,
        password: password
      }
    }) : window.confirm("Access Deneid", "Please Enter Correct Email And Password");
  }

  addEmployee() {
    this.setState({
      addEmployee: true,
    })
  }

  cancelAddEmployee() {
    this.setState({
      addEmployee: false,
    })
  }

  cancelViewEmployee() {
    this.setState({
      addEmployee: false,
      setEmployee: null
    })
  }

  addEmployeeData() {

    const date = new Date();
    const firstName = document.getElementById(`firstName`).value;
    const lastName = document.getElementById(`lastName`).value;
    const email2 = document.getElementById(`email2`).value;
    const nic = document.getElementById(`nic`).value;
    let body = {
      "firstName": firstName,
      "lastName": lastName,
      "email": email2,
      "phoneNumber": "0774854587",
      "address": "Colombo",
      "designationId": 1,
      "departmentId": 1,
      "nic": nic
    }
    try {
      fetch('http://localhost:1724/employee-system/api/v1/employee', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(body)
      }).then(response => {
        this.setState({ isButtonDisable: true })
        if (response.status === 200) {
          response.json().then(data => {
            console.log(data)
            window.confirm(data.message);
            this.getAll()
          })
        } else {
          this.setState({ redirect: true })
          console.log(response)
        }

      })
    } catch (error) {

    }

  }

  logOut() {
    this.setState({
      user: false
    })

  }

  deleteEmployee(index, value) {
    const empList = this.state.employeeList;
    empList.splice(index, 1)
    try {
      fetch(`http://localhost:1724/employee-system/api/v1/employee/${value.id}`, {
        method: 'DELETE'
      }).then(response => {
        this.setState({ isButtonDisable: true })
        if (response.status === 200) {
          response.json().then(data => {
            window.confirm(data.message);
            this.getAll()
          })
        } else {
          this.setState({ redirect: true })
          console.log(response)
        }

      })
    } catch (error) {

    }
    this.setState({
      empList
    })
  }

  async setViewFamily(value) {
    await this.setState(
      {
        setEmployee: value,
        addEmployee: true,
      }
    )
  }

  editEmployee(index) {
    this.setState(
      {
        editIndex: index
      }
    )
  }

  canceleditEmployee() {
    this.setState({
      editIndex: null
    })
  }

  updateEmployee(value) {

    let body = {
      "id": value.id,
      "firstName": this.state.editedFirstName?this.state.editedFirstName:value.firstName,
      "lastName": this.state.editedLastName?this.state.editedLastName:value.lastName,
      "email": this.state.editedEmail?this.state.editedEmail:value.email,
      "phoneNumber": "0771889654212",
      "address": "tampasitty",
      "designationId": 1,
      "departmentId": 1,
      "nic": this.state.editedNIC?this.state.editedNIC:value.nic
    }
    try {
      fetch('http://localhost:1724/employee-system/api/v1/employee', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify(body)
      }).then(response => {
        this.setState({ isButtonDisable: true })
        if (response.status === 200) {
          response.json().then(data => {
            window.confirm(data.message);
            this.getAll()
          })
        } else {
          this.setState({ redirect: true })
          console.log(response)
        }

      })
    } catch (error) {

    }

    this.setState({
      editIndex: null
    })
  }

  updateFirstName(e) {
    this.setState({
      editedFirstName: e.target.value
    }
    )
  }

  updateLastName(e) {
    this.setState({
      editedLastName: e.target.value
    })
  }

  updateEmail(e) {
    this.setState({
      editedEmail: e.target.value
    })
  }

  updateNIC(e) {
    this.setState({
      editedNIC: e.target.value
    })
  }

  //JSX Rendering Functions

  renderHeader() {
    return (
      <header className="App-header">
        <h1 className="App-title">Welcome To Employee Management System</h1>
      </header>
    )
  }

  renderLogin() {
    return (
      <div className="loginForm">
        <h1 className="loginFormHeader"><b>Login</b></h1>
        <form>
          <div className="form-group">
            <label >Email address</label>
            <input type="email" className="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email" />
          </div>
          <div className="form-group">
            <label >Password</label>
            <input type="password" className="form-control" id="password" placeholder="Password" />
          </div>
          <button type="button" className="btn btn-primary" onClick={() => { this.login() }}>Login</button>
        </form>
      </div>
    )
  }

  rendertoDoList() {
    return (

      <div className="renderTodoList">
        <h1 className="todoHeader">Employee List</h1>
        <div className="employeeList">
          <table className="table table-striped table-dark">
            <thead>
              <tr>
                <th scope="col" className="centerAll">#</th>
                <th scope="col" className="centerAll">First Name</th>
                <th scope="col" className="centerAll">Last Name</th>
                <th scope="col" className="centerAll">Email</th>
                <th scope="col" className="centerAll">NIC</th>
                <th scope="col" className="centerAll">Position</th>
                <th scope="col" className="centerAll">View Family</th>
                <th scope="col" className="centerAll">Edit</th>
                <th scope="col" className="centerAll">Delete</th>
              </tr>
            </thead>
            <tbody>
              {this.state.employeeList.map((value, index) => {
                return (
                  this.state.editIndex !== index ? <tr>
                    <th scope="row" id={index + 1}>{index + 1}</th>
                    <td className="centerAll" id={index + 2}>{value.firstName}</td>
                    <td className="centerAll" id={index + 3}>{value.lastName}</td>
                    <td className="centerAll" id={index + 4}>{value.email}</td>
                    <td className="centerAll" id={index + 5}>{value.nic}</td>
                    <td className="centerAll" id={index + 6}>{value.designationName}</td>
                    <td className="centerAll" id={index + 9}><button onClick={() => {
                      this.setViewFamily(value)
                    }} className="btn btn-secondary">View Family</button></td>
                    <td className="centerAll" id={index + 7}><button onClick={() => {
                      this.editEmployee(index)
                    }} className="btn btn-primary">Edit</button></td>
                    <td className="centerAll" id={index + 8}><button onClick={() => {
                      this.deleteEmployee(index, value)
                    }} className="btn btn-danger">Delete</button></td>
                  </tr>
                    : <tr>
                      <th scope="row" id={index + 1}>{index + 1}</th>
                      <td className="centerAll" id={index + 2 + 'edit'}><input type="text" defaultValue={value.firstName} onChange={this.updateFirstName} /></td>
                      <td className="centerAll" id={index + 3 + 'edit'}><input type="text" defaultValue={value.lastName} onChange={this.updateLastName} /></td>
                      <td className="centerAll" id={index + 4 + 'edit'}><input type="text" defaultValue={value.email} onChange={this.updateEmail} /></td>
                      <td className="centerAll" id={index + 5 + 'edit'}><input type="text" defaultValue={value.nic} onChange={this.updateNIC} /></td>
                      <td className="centerAll" id={index + 6 + 'edit'}>{value.designationName}</td>
                      <td className="centerAll" id={index + 9 + 'edit'}><button onClick={() => {
                        this.setViewFamily(value)
                      }} className="btn btn-secondary">View Family</button></td>
                      <td className="centerAll" id={index + 7 + 'edit'}><button onClick={() => {
                        this.canceleditEmployee()
                      }} className="btn btn-primary">Cancel</button></td>
                      <td className="centerAll" id={index + 8 + 'edit'}><button onClick={() => {
                        this.updateEmployee(value)
                      }} className="btn btn-info">Update</button></td>
                    </tr>
                )
              })}
            </tbody>
          </table>
        </div>
        <a class="btn-floating btn-large waves-effect waves-light green" onClick={() => {
          this.addEmployee()
        }}><i class="material-icons">+</i></a>
      </div>
    )

  }

  renderAddEmployee() {
    return (
      <div className="loginForm">
        <h1 className="todoHeader">Add Employee</h1>
        <form className="addEmployeeForm">
          <div className="form-group">
            <label >First Name</label>
            <input type="text" className="form-control" id="firstName" aria-describedby="emailHelp" placeholder="Enter First Name" />
          </div>
          <div className="form-group">
            <label >Last Name</label>
            <input type="text" className="form-control" id="lastName" aria-describedby="emailHelp" placeholder="Enter Last Name" />
          </div>
          <div className="form-group">
            <label >Email address</label>
            <input type="email" className="form-control" id="email2" aria-describedby="emailHelp" placeholder="Enter email" />
          </div>
          <div className="form-group">
            <label >NIC</label>
            <input type="text" className="form-control" id="nic" aria-describedby="emailHelp" placeholder="Enter NIC" />
          </div>
          <a class="btn-floating btn-large waves-effect waves-light blue  " onClick={() => {
            this.addEmployeeData()
          }}><i class="material-icons">+</i></a>
        </form>
        <button className="btn btn-danger addEmployeeForm" onClick={() => {
          this.cancelAddEmployee()
        }}>Cancel</button>
      </div>
    )
  }

  renderViewDetails() {
    return (
      <div className="family">
        <h1 className="todoHeader">Employee Details</h1>
        {/* <form className="addEmployeeForm"> */}
        <div class="text-center">
          <h3 >First Name : {this.state.setEmployee.firstName}</h3>
          <h3 >Last Name : {this.state.setEmployee.lastName}</h3>
          <h3 >Email address : {this.state.setEmployee.email}</h3>
          <h3 >NIC : {this.state.setEmployee.nic}</h3>
          <h3 >Phone Number : {this.state.setEmployee.phoneNumber}</h3>
          <h3 >Address : {this.state.setEmployee.address}</h3>
          <h3 >Position : {this.state.setEmployee.designationName}</h3>
        </div><br />
        {/* </form> */}
        <button className="btn btn-danger addEmployeeForm" onClick={() => {
          this.cancelViewEmployee()
        }}>Cancel</button>
      </div>
    )
  }

  renderLogOut() {
    return (
      <div className="logOut">
        <button className="btn btn-warning" onClick={() => {
          this.logOut();
        }}>Log Out</button>
      </div>
    )
  }

  render() {
    return (
      <div className="App">
        {this.renderHeader()}
        <br />
        {!this.state.user && <div>Use email: <strong>admin@domain.com</strong> and password: <strong>admin</strong> as login credentials</div>}
        {!this.state.user && this.renderLogin()}
        {this.state.user && !this.state.addEmployee && this.rendertoDoList()}
        {this.state.addEmployee && !this.state.setEmployee && this.renderAddEmployee()}
        {this.state.addEmployee && this.state.setEmployee && this.renderViewDetails()}
        {this.state.user && !this.state.addEmployee && this.renderLogOut()}
      </div>
    );
  }
}

export default App;
