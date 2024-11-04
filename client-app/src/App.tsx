import './App.css'
import LoginPage from "./pages/LoginPage";
import {QueryClient, QueryClientProvider} from "react-query";
import {RouterProvider, createBrowserRouter} from "react-router-dom";
import {HelmetProvider} from "react-helmet-async";
import {Provider} from "react-redux";
import {store} from "./store";
import QueueMainPage from "./pages/QueueMainPage";

const router = createBrowserRouter([
    {
        path: "/login",
        element: <LoginPage/>,
    },
    {
        path: "/queueMainPage",
        element: <QueueMainPage/>,
    }
]);

function App() {

    const queryClient = new QueryClient();

    return (
        <div className="App">
            <HelmetProvider>
                <QueryClientProvider client={queryClient}>
                    <Provider store={store}>
                        <RouterProvider router={router}/>
                    </Provider>
                </QueryClientProvider>
            </HelmetProvider>
        </div>
    )
}

export default App
