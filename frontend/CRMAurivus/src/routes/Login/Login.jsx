import sendLoginCreditials from "../../api/loginFetch";
import CustomForm from "../../components/form/CustomForm";
import "./login.css";

export default function Login () {
    const arrayData = [
        {
            name: "username",
            type: "text"
        },
        {
            name: "password",
            type: "password"
        }
    ];

    const handleSubmit = (data) =>{
        console.log("login route", data)
        sendLoginCreditials(data={data});
    }
    return (
     <div className="loginForm">
        <CustomForm title={"Login"} arrayData={arrayData} parentHandleSubmit={handleSubmit} />     
     </div>
    );
  }