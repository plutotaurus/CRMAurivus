import PropTypes from "prop-types";

export default function Button({ text, type, buttonColor }) {
    Button.propTypes = {
        text: PropTypes.string.isRequired,
        type: PropTypes.string,
        buttonColor: PropTypes.string.isRequired,
      };

    const buttonStyles = {
      backgroundColor: buttonColor,  
    };
  
    return (
      <button style={buttonStyles} type={type}>
        {text}
      </button>
    );
  }
