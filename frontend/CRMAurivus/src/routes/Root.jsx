import { Outlet } from "react-router-dom";
import Header from "../components/general/Header";
import "../components/general/general.css";
import Footer from "../components/general/Footer";

export default function Root() {
  return (
    <div>
      <Header />
      <Outlet />
      <Footer />
    </div>
  );
}
