import { Outlet } from "react-router-dom";
import Header from "../../components/general/Header";
import Footer from "../../components/general/Footer";
import "./root.css";

export default function Root() {
  return (
    <div className="root-container">
      <Header />
      <div className="content">
      <Outlet />
      </div>
      <Footer />
    </div>
  );
}
