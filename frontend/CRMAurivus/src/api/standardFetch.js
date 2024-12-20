import PropTypes from "prop-types";
const BASE_URL = "http://localhost:8080/";

export default async function Fetch({
  method,
  authenticationNeeded,
  token,
  requestBody,
  url,
}) {
  Fetch.propTypes = {
    url: PropTypes.string.isRequired,
    method: PropTypes.string.isRequired,
    authenticationNeeded: PropTypes.string.isRequired,
    requestBody: PropTypes.object,
    token: PropTypes.string,
  };
  const JSONBody = JSON.stringify(requestBody);
  const headers = {
    "Content-Type": "application/json",
  };

  if (authenticationNeeded) {
    headers["Authorization"] = `Bearer ${token}`;
  }

  const res = await fetch(`${BASE_URL + url}`, {
    mode: "cors",
    method: method,
    headers: headers,
    body: JSONBody,
  });
  return res;
}

export function removeTokenFromCookie() {
  document.cookie = "token=; Max-Age=0; path=/;";
}
