import React from 'react';

const ${className} = () => {
    return (
        <div>
            <h1>${className} Component</h1>
            <ul>
                <#list fields as field>
                    <li>${field.name}: <input type="${field.type}" name="${field.name}" /></li>
                </#list>
            </ul>
        </div>
    );
}

export default ${className};
