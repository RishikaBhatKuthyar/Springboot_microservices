import axios from 'axios'
const EMPLOYEE_SERVICE_BASE_URL="http://localhost:9191/api/emp/show"

const EMPLOYEE_ID=1;

class EmployeeService{

    getEmployee(){
        return axios.get(EMPLOYEE_SERVICE_BASE_URL+'/'+ EMPLOYEE_ID )
    }
}

export default new EmployeeService