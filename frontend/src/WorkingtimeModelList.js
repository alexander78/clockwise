import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link, withRouter } from 'react-router-dom'

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
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"../workingtimemodel/" + model.id + "/"+this.props.match.params.userid}>Edit</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });
    
        return <div>
                <Container fluid>
                    <AppNavbar/>
                    <div className="float-right">
                        <Button color="success" tag={Link} to={"/workingtimemodel/new/" + this.props.match.params.userid}>Add Workingtimemodel</Button>
                    </div>
                    <h3>Workingtimemodels</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Urlaubstage</th>
                            <th width="25%">Gültig von</th>
                            <th width="25%">Gültig bis</th>
                            <th width="30%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {WorkingtimeModelList}
                        </tbody>
                    </Table>
                    <div className="float-right">
                        <Button color="secondary" tag={Link} to="/users">Cancel</Button>
                    </div>
                </Container>
            </div>
    }
}

export default withRouter(WorkingtimeModelList);