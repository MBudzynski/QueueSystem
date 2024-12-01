import React from 'react';
import { store } from "./store";
import {QueryClient, QueryClientProvider} from "react-query";
import {RouterProvider, createBrowserRouter} from "react-router-dom";
import {HelmetProvider} from "react-helmet-async";
import {Provider} from "react-redux";
import './App.css';
import MainPage from "./pages/MainPage";

const router = createBrowserRouter([
    {
        path: "/",
        element: <MainPage/>,
    },
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
