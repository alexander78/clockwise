import React, { Component } from 'react';



class RoleSelectionComponent extends Component {
    
    onChangeRole = (event) => {
        this.props.onChangeRole(event);
    }

    render() {
        const { roles } = this.props;
        console.log(roles);
        return (
            <div>
                <span>Select Role</span>
                {roles && roles.length > 0 && (
                    <div>
                        <select  class="form-select" onChange={this.onChangeRole}>
                        {roles.map((role, index) => {
                            return <option>{role.roleName}</option>;
                        })}
                        </select>
                    </div>
                )}
            </div>
        );
    }
}
export default RoleSelectionComponent;