import PropTypes from "prop-types";
export default function InputFieldWithLabel({ name, type, value, handleOnChange }) {
  InputFieldWithLabel.propTypes = {
    name: PropTypes.string.isRequired,
    type: PropTypes.string.isRequired,
    value: PropTypes.string.isRequired,
    handleOnChange: PropTypes.func.isRequired,
  };
  return (
    <div className="inputfieldAndLabel">
      <label htmlFor={name}>{name}</label>
      <input type={type} id={name} name={name} value={value} onChange={handleOnChange} />
    </div>
  );
}
