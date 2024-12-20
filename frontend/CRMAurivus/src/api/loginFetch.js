import PropTypes from "prop-types";
import Fetch, {
  addTokenToCookie,
  removeTokenFromCookie,
} from "./standardFetch";

export default async function sendLoginCreditials({ data }) {
  sendLoginCreditials.propTypes = {
    data: PropTypes.object.isRequired,
  };
    const res = await Fetch({
    method: "POST",
    authenticationNeeded: false,
    requestBody: data,
    url: "auth/login",
  });
  checkLoginResponse(res);
}

export async function checkLoginResponse(response) {
  checkLoginResponse.propTypes = {
    response: PropTypes.object.isRequired,
  };
  switch (response.status) {
    case 200: {
      const token = await response.json();
      setTokenInCookie(token);
      break;
    }

    case 401: {
      const errorData = await response.json();
      throw new Error(errorData.message);
    }
  }
}

export function setTokenInCookie({ token }) {
  removeTokenFromCookie();
  addTokenToCookie(token);
}
