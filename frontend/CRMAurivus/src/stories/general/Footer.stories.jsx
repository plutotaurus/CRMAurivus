import { expect, within } from "@storybook/test";
import { MemoryRouter } from "react-router-dom";
import '../../components/general/general.css'
import Footer from "../../components/general/Footer";


export default {
    title: "Footer",
    component: Footer,
    decorators: [
      (Story) => (
        <MemoryRouter>
            <Story />
        </MemoryRouter>
      ),
    ],
};

export const FooterRenders ={
    play: async ({ canvasElement }) => {
        const canvas = within(canvasElement);
        
        //Arrange 
        const textElement = canvas.getByText("\u00a9 Aurivus Innovations")
    
        //Assert
        expect(textElement).toBeInTheDocument();
      },

}