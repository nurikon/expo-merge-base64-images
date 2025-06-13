import * as React from 'react';

import { ExpoMergeBase64ImagesViewProps } from './ExpoMergeBase64Images.types';

export default function ExpoMergeBase64ImagesView(props: ExpoMergeBase64ImagesViewProps) {
  return (
    <div>
      <iframe
        style={{ flex: 1 }}
        src={props.url}
        onLoad={() => props.onLoad({ nativeEvent: { url: props.url } })}
      />
    </div>
  );
}
