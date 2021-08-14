import React, { Component } from 'react';
import { Button, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom'

class WorkingtimeModelList extends Component {

    constructor(props) {
        super(props);
        this.state = {workingtimemodels: []};
    }

    async componentDidMount() {

        fetch(`/api/workingtimemodel/getAllByUser/${this.props.match.params.userid}`)
            .then(response => response.json())
            .then(data => this.setState({workingtimemodels: data}));

    }

    render() {
        const {workingtimemodels, isLoading} = this.state;
    
        if (isLoading) {
            return <p>Loading...</p>;
        }
    
        const WorkingtimeModelList = workingtimemodels.map(model => {
            return <tr key={model.id}>
                <td style={{whiteSpace: 'nowrap'}}>{model.vacationDays}</td>
                <td>{model.validFrom}</td>
                <td>{model.validTo}</td>
            </tr>
        });
    
        return <div>
                <Container fluid>
                    <AppNavbar/>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/workingtimemodel/new">Add Workingtimemodel</Button>
                    </div>
                    <h3>Workingtimemodels</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="30%">Urlaubstage</th>
                            <th width="30%">Gültig von</th>
                            <th width="30%">Gültig bis</th>
                        </tr>
                        </thead>
                        <tbody>
                        {WorkingtimeModelList}
                        </tbody>
                    </Table>
                </Container>
            </div>
                                                                                                                                                                              
    }
}

export default WorkingtimeModelList;