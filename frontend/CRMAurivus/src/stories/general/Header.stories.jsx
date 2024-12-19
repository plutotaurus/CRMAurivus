import Header from "../../components/general/Header";
import '../../components/general/general.css'
import { expect, within } from "@storybook/test";

import { MemoryRouter } from "react-router-dom";


export default {
    title: "Header",
    component: Header,
    decorators: [
      (Story) => (
        <MemoryRouter>
            <Story />
        </MemoryRouter>
      ),
    ],
};

export const HeaderRenders ={
    play: async ({ canvasElement }) => {
        const canvas = within(canvasElement);
    
        //Arrange
        const logoElement = canvas.getByAltText("Aurivus Logo");
        const titleElement = canvas.getByText("Aurivus Innovations")
    
        //Assert
        expect(logoElement).toBeInTheDocument();
        expect(titleElement).toBeInTheDocument();
      },

}