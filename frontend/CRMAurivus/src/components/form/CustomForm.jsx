import PropTypes from "prop-types";
import { Form } from "react-router-dom";
import InputFieldWithLabel from "./Inputfield";
import "./form.css";
import Button from "../general/Button";
import { useState } from "react";

export default function CustomForm({ title, arrayData, parentHandleSubmit}) {
  CustomForm.propTypes = {
    title: PropTypes.string.isRequired,
    arrayData: PropTypes.array.isRequired,
    parentHandleSubmit: PropTypes.func.isRequired
  };

  const [formData, setFormData] = useState(() => {
    const initialData = {};
    arrayData.forEach((item) => {
      initialData[item.name] = "";  
    });
    return initialData;
  });

  const handleInput = (event) => {
    const { name, value } = event.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value, 
    }));
  };
  const handleSubmit = async (event) => {
    event.preventDefault();
    parentHandleSubmit(formData);

  }  
  return (
    <div className="form">
      <h1>{title}</h1>
      <Form method="post" onSubmit={handleSubmit} >
        {arrayData.map((item) => (
          <InputFieldWithLabel
            key={item.name}
            name={item.name}
            type={item.type}
            value={formData[item.name]}
            handleOnChange={handleInput} 
          />
        ))}
        <Button text={"Log in"} type={"submit"} buttonColor={"var(--yellow)"}/>
      </Form>
    </div>
  );
}
