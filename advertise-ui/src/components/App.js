import React from 'react';
import { Router, Route, Switch } from 'react-router-dom';
import PageNotFound from './common/PageNotFound';
import Home from './landing/Home';
import AdvertiseListContainer from './advertise/AdvertiseListContainer'; // eslint-disable-line import/no-named-as-default
import AdvertiseSearchContainer from './search/AdvertiseSearchContainer'; // eslint-disable-line import/no-named-as-default
import AddOrEditAdvertiseContainer from './advertise/AddOrEditAdvertiseContainer'; // eslint-disable-line import/no-named-as-default
import About from './About';
import createBrowserHistory from 'history/createBrowserHistory';
import HeaderNavContainer from './template/HeaderNavContainer'; // eslint-disable-line import/no-named-as-default



const history = createBrowserHistory();


const App = () => {
    return (
        <div >
            <Router history={history}>
                <div>

                    <HeaderNavContainer />

                    <Switch>
                        <Route exact path="/" component={Home} />
                        <Route path="/advertises" component={AdvertiseListContainer} />
                        <Route exact path="/advertise" component={AddOrEditAdvertiseContainer} />
                        <Route path="/advertise/:id" component={AddOrEditAdvertiseContainer} />
                        <Route path="/search" component={AdvertiseSearchContainer} />
                        <Route path="/about" component={About} />
                        <Route component={PageNotFound} />
                    </Switch>

                </div>

            </Router>
        </div>
    );
};


export default App;
